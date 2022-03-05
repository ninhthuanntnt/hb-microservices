package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.CommentMapper;
import com.ntnt.highblog.dmm.model.entity.Comment;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.request.CommentCreateReq;
import com.ntnt.highblog.dmm.model.request.CommentUpdateReq;
import com.ntnt.highblog.dmm.service.CommentService;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.neo4j.PostNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CommentCrudBloc {

    private final CommentService commentService;
    private final PostStatisticService postStatisticService;
    private final PostNodeService postNodeService;
    //TODO: Use notification client
//    private final NotificationBloc notificationBloc;

    public CommentCrudBloc(final CommentService commentService,
                           final PostStatisticService postStatisticService,
                           final PostNodeService postNodeService) {
        this.commentService = commentService;
        this.postStatisticService = postStatisticService;
//        this.notificationBloc = notificationBloc;
        this.postNodeService = postNodeService;
    }

    @Transactional
    public Long createCommentForCurrentUser(final CommentCreateReq commentCreateReq) {
        log.info("Create comment with data #{}", commentCreateReq);

        Comment comment = CommentMapper.INSTANCE.toComment(commentCreateReq);

        comment.setUserId(SecurityHelper.getCurrentUserId());

        commentService.saveNew(comment);
        PostStatistic postStatistic = postStatisticService.increaseNumberOfComments(commentCreateReq.getPostId());

        postNodeService.updateNumberOfComments(postStatistic.getPostId(),
                                               postStatistic.getNumberOfComments());
        return comment.getId();
    }


    @Transactional
    public void updateCommentForCurrentUser(final Long id, final CommentUpdateReq commentUpdateReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Update comment by comment id #{} for current user with user id #{}", id, userId);

        Comment comment = commentService.getByIdAndUserId(id, userId);

        comment.setContent(commentUpdateReq.getContent());

        commentService.save(comment);
    }

    @Transactional
    public void deleteCommentForCurrentUser(final Long id) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Delete comment by id #{} with userId #{}", id, userId);

        Long postId = commentService.getByIdAndUserId(id, userId).getPostId();

        commentService.delete(id, userId);
        PostStatistic postStatistic = postStatisticService.decreaseNumberOfComments(postId);

        postNodeService.updateNumberOfComments(postStatistic.getPostId(),
                                               postStatistic.getNumberOfComments());
    }
}
