package com.ntnt.highblog.notification.bloc;

import com.ntnt.highblog.notification.api.client.HbDmmClient;
import com.ntnt.highblog.notification.api.client.HbUaaClient;
import com.ntnt.highblog.notification.error.exception.ValidatorException;
import com.ntnt.highblog.notification.helper.SecurityHelper;
import com.ntnt.highblog.notification.mapper.NotificationMapper;
import com.ntnt.highblog.notification.model.dto.request.NotificationCreateReq;
import com.ntnt.highblog.notification.model.dto.response.AccountRes;
import com.ntnt.highblog.notification.model.dto.response.NotificationRes;
import com.ntnt.highblog.notification.model.dto.response.NotificationSenderRes;
import com.ntnt.highblog.notification.model.dto.response.SubscriptionRes;
import com.ntnt.highblog.notification.model.dto.response.UserRes;
import com.ntnt.highblog.notification.model.entity.Notification;
import com.ntnt.highblog.notification.service.NotificationService;
import com.ntnt.highblog.notification.service.UserNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NotificationBloc {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;
    private final UserNotificationService userNotificationService;
    //    private final SubscriptionService subscriptionService;
//    private final AccountService accountService;
    //    private final UserService userService;
    private final HbDmmClient hbDmmClient;
    private final HbUaaClient hbUaaClient;

    private static final String DEFAULT_NOTIFICATION_DESTINATION = "/exchange/amq.direct/notification";

    public NotificationBloc(final SimpMessagingTemplate simpMessagingTemplate,
                            final NotificationService notificationService,
                            final UserNotificationService userNotificationService,
                            final HbDmmClient hbDmmClient,
                            final HbUaaClient hbUaaClient) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
        this.userNotificationService = userNotificationService;
        this.hbDmmClient = hbDmmClient;
        this.hbUaaClient = hbUaaClient;
    }

    @Async
    public void pushNotificationToFollowers(final Long senderId,
                                            final Notification notification) {
        log.info("Push notification to followers with senderId #{} and data #{}",
                 senderId,
                 notification);

        ResponseEntity<List<SubscriptionRes>> responseEntity = hbDmmClient.fetchSubscriptionsByUserId(senderId);

        if (ObjectUtils.isNotEmpty(responseEntity.getBody())) {
            pushNotificationTo(responseEntity.getBody()
                                             .stream()
                                             .map(SubscriptionRes::getFollowerId)
                                             .collect(Collectors.toList()),
                               notification);
        }

    }

    @Async
    public void pushNotificationTo(final List<Long> receiverIds,
                                   final Notification notification) {
        log.info("Push notification to receivers #{} with data #{}",
                 receiverIds,
                 notification);
        notificationService.saveNew(notification);

        userNotificationService.saveNewAll(receiverIds, notification.getId());

        NotificationRes notificationRes = NotificationMapper.INSTANCE.toNotificationRes(notification);

        ResponseEntity<List<AccountRes>> responseEntity = hbUaaClient.fetchAccountsByUserIdIn(receiverIds);

        if (ObjectUtils.isNotEmpty(responseEntity.getBody())) {
            responseEntity.getBody()
                          .forEach(accountRes ->
                                       simpMessagingTemplate.convertAndSendToUser(accountRes.getUsername(),
                                                                                  DEFAULT_NOTIFICATION_DESTINATION,
                                                                                  notificationRes)
                          );
        }
    }

    @Async
    public void createNotification(final NotificationCreateReq notificationCreateReq) {

    }

    @Async
    public void pushNotificationTo(final Long receiverId,
                                   final Notification notification) {
        log.info("Push notification to receiver #{} with data #{}",
                 receiverId,
                 notification);
        pushNotificationTo(Collections.singletonList(receiverId), notification);
    }

    @Async
    public void deleteNotificationToFollowers(final Long sourceId) {
        log.info("Delete notification to followers by sourceId");

        notificationService.softDeleteBySourceId(sourceId);
    }

    @Transactional(readOnly = true)
    public void pushUnreadNotificationToCurrentUser() {
        Long userId = SecurityHelper.getCurrentUserId();
        List<Notification> unreadNotifications = notificationService.fetchUnreadNotifications(userId);

        String username = SecurityHelper.getCurrentUsername();


        List<NotificationRes> listNotificationRes = NotificationMapper.INSTANCE
            .toListNotificationRes(unreadNotifications);

        populateSenderIdForNotifications(listNotificationRes);

        listNotificationRes.forEach(notificationRes ->
                                        simpMessagingTemplate.convertAndSendToUser(username,
                                                                                   DEFAULT_NOTIFICATION_DESTINATION,
                                                                                   notificationRes));
    }

    @Transactional
    public void markSentNotificationForCurrentUser(final Long notificationId) {
        Long userId = SecurityHelper.getCurrentUserId();

        log.info("Mark as sent notification for current user #{}", userId);

        userNotificationService.markAsSentByNotificationIdAndUserId(notificationId, userId);
    }

    private void populateSenderIdForNotifications(final List<NotificationRes> notifications) {
        Set<Long> senderIds = notifications.stream()
                                           .map(NotificationRes::getSenderId)
                                           .collect(Collectors.toSet());

        ResponseEntity<List<UserRes>> responseEntity =
            hbDmmClient.fetchUsersByIdIn("Bearer " + SecurityHelper.getCurrentAccessToken(),
                                         new ArrayList<>(senderIds));

        if (responseEntity.getStatusCode().isError() || ObjectUtils.isEmpty(responseEntity.getBody())) {
            throw new ValidatorException("Error when get list user", "users");
        }

        Map<Long, List<UserRes>> senderIdsMap = responseEntity.getBody()
                                                              .stream()
                                                              .collect(Collectors.groupingBy(UserRes::getId));

        notifications.forEach(
            notificationRes -> {
                UserRes userRes = senderIdsMap.get(notificationRes.getSenderId()).get(0);
                notificationRes.setSenderRes(NotificationSenderRes.builder()
                                                                  .imagePath(userRes.getImagePath())
                                                                  .nickName(userRes.getNickName())
                                                                  .build());
            });
    }
}
