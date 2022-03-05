package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.constant.AppErrorCode;
import com.ntnt.highblog.dmm.enums.VoteType;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.mapper.PostVoteMapper;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.model.entity.PostVote;
import com.ntnt.highblog.dmm.model.request.PostVoteCreateReq;
import com.ntnt.highblog.dmm.model.request.PostVoteDeleteReq;
import com.ntnt.highblog.dmm.model.request.PostVoteUpdateReq;
import com.ntnt.highblog.dmm.service.PostStatisticService;
import com.ntnt.highblog.dmm.service.PostVoteService;
import com.ntnt.highblog.dmm.service.neo4j.PostNodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PostVoteCrudBloc {
    private final PostVoteService postVoteService;
    private final PostStatisticService postStatisticService;
    private final PostNodeService postNodeService;

    public PostVoteCrudBloc(final PostVoteService postVoteService,
                            final PostStatisticService postStatisticService,
                            final PostNodeService postNodeService) {
        this.postVoteService = postVoteService;
        this.postStatisticService = postStatisticService;
        this.postNodeService = postNodeService;
    }

    @Transactional
    public void createPostVoteForCurrentUser(final PostVoteCreateReq postVoteCreateReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Create post vote with data #{} for current user #{}", postVoteCreateReq, userId);

        validatePostVoteCreateReq(postVoteCreateReq);

        PostVote postVote = PostVoteMapper.INSTANCE.toPostVote(postVoteCreateReq);

        postVote.setUserId(userId);
        postVoteService.saveNew(postVote);

        PostStatistic postStatistic = postStatisticService.saveNumberOfVotes(postVoteCreateReq.getPostId(),
                                                                             null,
                                                                             postVoteCreateReq.getVoteType());

        postNodeService.updateNumberOfVotes(postStatistic.getPostId(),
                                            postStatistic.getNumberOfVotes());
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

        PostStatistic postStatistic = postStatisticService.saveNumberOfVotes(postVoteUpdateReq.getPostId(),
                                                                             currentVoteType,
                                                                             newPostVote.getVoteType());

        postNodeService.updateNumberOfVotes(postStatistic.getPostId(),
                                            postStatistic.getNumberOfVotes());
    }

    @Transactional
    public void deletePostVoteForCurrentUser(final PostVoteDeleteReq postVoteDeleteReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Delete post vote with data #{} for current user #{}", postVoteDeleteReq, userId);

        PostVote currentPostVote = postVoteService.getNullableByPostIdAndUserId(postVoteDeleteReq.getPostId(), userId);

        postVoteService.delete(currentPostVote);

        PostStatistic postStatistic = postStatisticService.saveNumberOfVotes(postVoteDeleteReq.getPostId(),
                                                                             currentPostVote.getVoteType(),
                                                                             null);

        postNodeService.updateNumberOfVotes(postStatistic.getPostId(),
                                            postStatistic.getNumberOfVotes());

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
