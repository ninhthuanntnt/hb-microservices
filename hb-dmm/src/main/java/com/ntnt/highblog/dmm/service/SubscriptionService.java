package com.ntnt.highblog.dmm.service;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import com.ntnt.highblog.dmm.error.exception.ValidatorException;
import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.Subscription;
import com.ntnt.highblog.dmm.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public SubscriptionService(final SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public boolean existsByUserIdAndFollowerId(final Long userId, final Long followerId) {
        log.info("Exists by userId #{} and followerId #{}", userId, followerId);

        return repository.existsByUserIdAndFollowerId(userId, followerId);
    }

    @Transactional(readOnly = true)
    public Subscription findNullableByUserIdAndFollowerId(final Long userId, final Long followerId) {
        log.info("Find by userId #{} and followerId #{}", userId, followerId);

        return repository.findByUserIdAndFollowerId(userId, followerId)
                         .orElse(null);
    }

    @Transactional
    public void saveNew(final Subscription subscription) {
        log.info("Save new subscription with data #{}", subscription);

        validatePostVoteBeforeSaveNew(subscription);

        repository.save(subscription);
    }

    @Transactional
    public void saveNew(final Long userId, final Long followerId) {
        log.info("Save new subscription with userId #{} and followerId #{}", userId, followerId);

        saveNew(Subscription.builder()
                            .userId(userId)
                            .followerId(followerId)
                            .build());
    }

    @Transactional
    public void delete(final Long userId, final Long followerId) {
        log.info("Delete subscription with userId #{} and followerId #{}", userId, followerId);

        Subscription subscription = repository.findByUserIdAndFollowerId(userId, followerId)
                                              .orElseThrow(() -> new ObjectNotFoundException("subscription"));

        repository.delete(subscription);
    }

    @Transactional(readOnly = true)
    public List<Subscription> fetchFollowerIdsByUserId(final Long userId) {
        log.info("Fetch follower ids by userId #{}", userId);

        return repository.findFollowerIdsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Long getNumberOfFollowersByUserId(final Long userId) {
        log.info("Get number of followers by user id #{}", userId);

        return repository.countByUserId(userId)
                         .orElse(0L);
    }

    private void validatePostVoteBeforeSaveNew(final Subscription subscription) {
        if (ObjectUtils.isNotEmpty(subscription.getId()))
            throw new ValidatorException("Invalid post id", "id");
        if (subscription.getFollowerId() != SecurityHelper.getCurrentUserId())
            throw new ValidatorException("Invalid follower id", "followerId");
        if (repository.existsByUserIdAndFollowerId(subscription.getUserId(), subscription.getFollowerId()))
            throw new ValidatorException("Already exists", "subscription");
    }

    @Transactional
    public void update(Long userId, Long followerId) {
        log.info("update subscription with userId #{} and followerId #{}", userId, followerId);

        Subscription subscription = repository.findByUserIdAndFollowerId(userId, followerId)
                                              .orElseThrow(() -> new ObjectNotFoundException("subscription"));
        boolean isNotNotified = subscription.isNotified();
        subscription.setNotified(!isNotNotified);
        repository.save(subscription);
    }
}
