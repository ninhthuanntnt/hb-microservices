package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.enums.VoteType;
import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.model.entity.PostStatistic;
import com.ntnt.highblog.dmm.repository.PostStatisticRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class PostStatisticService {

    private final PostStatisticRepository repository;

    public PostStatisticService(final PostStatisticRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<PostStatistic> fetchByPostIdIn(Collection<Long> postIds) {
        log.info("Fetch list post statistics by post id in #{}", postIds);

        return repository.findByPostIdIn(postIds);
    }

    @Transactional(readOnly = true)
    public PostStatistic getByPostId(Long postId) {
        log.info("Fetch list post statistics by postId #{}", postId);

        return repository.findByPostId(postId)
                         .orElseThrow(() -> new ObjectNotFoundException("postStatistic"));
    }

    @Transactional
    public void saveNew(PostStatistic postStatistic) {
        log.info("Save new post statistic with data #{}", postStatistic);
        validatePostBeforeSaveNew(postStatistic);

        repository.save(postStatistic);
    }

    @Transactional
    public PostStatistic saveNumberOfVotes(final Long postId, final VoteType currentVoteType, final VoteType newVoteType) {
        log.info("Save numberOfVote for postId #{} by previousVoteType #{} and newVoteType #{}",
                 postId,
                 currentVoteType,
                 newVoteType);

        PostStatistic postStatistic = repository.findByPostId(postId)
                                                .orElseThrow(() -> new ObjectNotFoundException("postStatistic"));
        postStatistic.updateNumberOfVotes(currentVoteType, newVoteType);
        return repository.save(postStatistic);
    }

    @Transactional
    public PostStatistic increaseNumberOfFavorite(final Long postId) {
        log.info("Increase numberOfFavorites");
        PostStatistic postStatistic = repository.findByPostId(postId)
                                                .orElseThrow(()->new ObjectNotFoundException("postStatistic"));

        postStatistic.setNumberOfFavorites(postStatistic.getNumberOfFavorites() + 1);

        return repository.save(postStatistic);
    }

    @Transactional
    public PostStatistic decreaseNumberOfFavorite(final Long postId) {
        log.info("Decrease numberOfFavorites");
        PostStatistic postStatistic = repository.findByPostId(postId)
                                                .orElseThrow(()->new ObjectNotFoundException("postStatistic"));

        postStatistic.setNumberOfFavorites(postStatistic.getNumberOfFavorites() - 1);

        return repository.save(postStatistic);
    }

    @Transactional
    public PostStatistic increaseNumberOfComments(final Long postId) {
        log.info("Increase numberOfComments");
        PostStatistic postStatistic = repository.findByPostId(postId)
                                                .orElseThrow(()->new ObjectNotFoundException("postStatistic"));

        postStatistic.setNumberOfComments(postStatistic.getNumberOfComments() + 1);

        return repository.save(postStatistic);
    }

    @Transactional
    public PostStatistic decreaseNumberOfComments(final Long postId) {
        log.info("Decrease numberOfFavorites");
        PostStatistic postStatistic = repository.findByPostId(postId)
                                                .orElseThrow(()->new ObjectNotFoundException("postStatistic"));

        postStatistic.setNumberOfComments(postStatistic.getNumberOfComments() - 1);

        return repository.save(postStatistic);
    }

    private void validatePostBeforeSaveNew(final PostStatistic postStatistic) {
        if (ObjectUtils.isNotEmpty(postStatistic.getId()))
            throw new ValidatorException("Invalid post statistic id", "id");
    }
}
