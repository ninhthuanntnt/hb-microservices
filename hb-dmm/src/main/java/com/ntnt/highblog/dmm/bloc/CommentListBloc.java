package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.PaginationHelper;
import com.ntnt.highblog.dmm.model.entity.Comment;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.model.request.CommentListReq;
import com.ntnt.highblog.dmm.service.CommentService;
import com.ntnt.highblog.dmm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommentListBloc {
    private final CommentService commentService;
    private final UserService userService;

    public CommentListBloc(final CommentService commentService,
                       final UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }


    @Transactional(readOnly = true)
    public List<Comment> fetchCommentsByPostId(final CommentListReq commentListReq) {
        log.info("Fetch comments with data #{}", commentListReq);
        PageRequest pageRequest = PaginationHelper.generatePageRequest(commentListReq);
        List<Comment> comments = commentService.fetchByPostIdWithPageRequest(commentListReq.getPostId(),
                                                                             pageRequest);

        includeUserToComments(comments);

        return populateComments(comments);
    }

    private void includeUserToComments(List<Comment> comments) {
        Map<Long, User> users = userService.fetchByIdIn(comments.stream()
                                                                .map(Comment::getUserId)
                                                                .collect(Collectors.toList()))
                                           .stream()
                                           .collect(Collectors.toMap(User::getId, user -> user));

        comments.forEach(comment -> comment.setUser(users.get(comment.getUserId())));
    }

    private List<Comment> populateComments(final List<Comment> comments) {
        List<Comment> rootComment = comments.stream()
                                            .filter(comment -> ObjectUtils.isEmpty(comment.getParentId()))
                                            .collect(Collectors.toList());

        Map<Long, List<Comment>> childCommentsMap =
                comments.stream()
                        .filter(comment -> ObjectUtils.isNotEmpty(comment.getParentId()))
                        .sorted((o1, o2) -> Long.compare(o2.getId(), o1.getId()))
                        .collect(Collectors.groupingBy(Comment::getParentId));


        rootComment.forEach(comment -> comment.setChildComments(childCommentsMap.get(comment.getId())));

        return rootComment;
    }
}
