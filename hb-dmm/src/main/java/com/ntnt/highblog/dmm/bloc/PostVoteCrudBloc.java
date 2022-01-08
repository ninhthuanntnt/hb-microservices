package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.constant.AppErrorCode;
import com.ntnt.highblog.dmm.enums.VoteType;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.PostVoteMapper;
import com.ntnt.highblog.dmm.model.entity.PostVote;
import com.ntnt.highblog.dmm.model.request.PostVoteCreateReq;
import com.ntnt.highblog.dmm.model.request.PostVoteDeleteReq;
import com.ntnt.highblog.dmm.model.request.PostVoteUpdateReq;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.PostVoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PostVoteCrudBloc {
    private final PostVoteService postVoteService;
    private final PostStatisticService postStatisticService;

    public PostVoteCrudBloc(final PostVoteService postVoteService,
                            final PostStatisticService postStatisticService) {
        this.postVoteService = postVoteService;
        this.postStatisticService = postStatisticService;
    }

    @Transactional
    public void createPostVoteForCurrentUser(final PostVoteCreateReq postVoteCreateReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Create post vote with data #{} for current user #{}", postVoteCreateReq, userId);

        validatePostVoteCreateReq(postVoteCreateReq);

        PostVote postVote = PostVoteMapper.INSTANCE.toPostVote(postVoteCreateReq);

        postVote.setUserId(userId);
        postVoteService.saveNew(postVote);


        postStatisticService.saveNumberOfVotes(postVoteCreateReq.getPostId(),
                                               null,
                                               postVoteCreateReq.getVoteType());
    }

    @Transactional
    public void updatePostVoteForCurrentUser(final PostVoteUpdateReq postVoteUpdateReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Create post vote with data #{} for current user #{}", postVoteUpdateReq, userId);

        PostVote currentPostVote = postVoteService.getByPostIdAndUserId(postVoteUpdateReq.getPostId(), userId);
        VoteType currentVoteType = currentPostVote.getVoteType();

        validateCurrentPostVoteAndPostVoteUpdateReq(currentPostVote, postVoteUpdateReq);

        PostVote newPostVote = PostVoteMapper.INSTANCE.toPostVote(postVoteUpdateReq, currentPostVote);
        postVoteService.save(newPostVote);

        postStatisticService.saveNumberOfVotes(postVoteUpdateReq.getPostId(),
                                               currentVoteType,
                                               newPostVote.getVoteType());
    }

    @Transactional
    public void deletePostVoteForCurrentUser(final PostVoteDeleteReq postVoteDeleteReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Delete post vote with data #{} for current user #{}", postVoteDeleteReq, userId);

        PostVote currentPostVote = postVoteService.getNullableByPostIdAndUserId(postVoteDeleteReq.getPostId(), userId);

        postVoteService.delete(currentPostVote);

        postStatisticService.saveNumberOfVotes(postVoteDeleteReq.getPostId(),
                                               currentPostVote.getVoteType(),
                                               null);

    }

    private void validatePostVoteCreateReq(final PostVoteCreateReq postVoteCreateReq) {
        PostVote postVote = postVoteService.getNullableByPostIdAndUserId(postVoteCreateReq.getPostId(),
                                                                         SecurityHelper.getCurrentUserId());
        if (ObjectUtils.isNotEmpty(postVote))
            throw new ValidatorException("Already vote", AppErrorCode.DEFAULT_VALIDATOR);
    }

    private void validateCurrentPostVoteAndPostVoteUpdateReq(final PostVote currentPostVote,
                                                             final PostVoteUpdateReq postVoteUpdateReq) {
        if (currentPostVote.getVoteType().equals(postVoteUpdateReq.getVoteType()))
            throw new ValidatorException("Already vote", AppErrorCode.DEFAULT_VALIDATOR);
    }
}
