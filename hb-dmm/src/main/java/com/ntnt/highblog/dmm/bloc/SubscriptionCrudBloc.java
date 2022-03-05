package com.ntnt.highblog.dmm.bloc;

import com.ntnt.highblog.dmm.helper.SecurityHelper;
import com.ntnt.highblog.dmm.model.entity.User;
import com.ntnt.highblog.dmm.service.SubscriptionService;
import com.ntnt.highblog.dmm.service.UserService;
import com.ntnt.highblog.dmm.service.neo4j.UserNodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class SubscriptionCrudBloc {

    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final UserNodeService userNodeService;

    public SubscriptionCrudBloc(final SubscriptionService subscriptionService,
                                final UserService userService,
                                final UserNodeService userNodeService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.userNodeService = userNodeService;
    }

    @Transactional
    public void createSubscriptionForCurrentUser(final String nickName) {
        log.info("Create subscription for current user");

        Long followerId = SecurityHelper.getCurrentUserId();

        User user = userService.getByNickName(nickName);

        subscriptionService.saveNew(user.getId(), followerId);
        userNodeService.createFollowsRelationship(followerId, user.getId());
    }

    @Transactional
    public void deleteSubscriptionForCurrentUser(final String nickName) {
        log.info("Delete subscription for current user");

        Long followerId = SecurityHelper.getCurrentUserId();

        User user = userService.getByNickName(nickName);

        subscriptionService.delete(user.getId(), followerId);
        userNodeService.deleteFollowsRelationship(followerId, user.getId());
    }
    @Transactional
    public void updateNotifiedStatus(String nickName) {
        log.info("Update notified status");

        Long followerId = SecurityHelper.getCurrentUserId();

        User user = userService.getByNickName(nickName);

        subscriptionService.update(user.getId(), followerId);
    }
}
