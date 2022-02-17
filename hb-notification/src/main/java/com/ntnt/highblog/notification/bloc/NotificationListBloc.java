package com.ntnt.highblog.notification.bloc;

import com.ntnt.highblog.notification.api.client.HbDmmClient;
import com.ntnt.highblog.notification.error.exception.ValidatorException;
import com.ntnt.highblog.notification.helper.PaginationHelper;
import com.ntnt.highblog.notification.helper.SecurityHelper;
import com.ntnt.highblog.notification.mapper.NotificationMapper;
import com.ntnt.highblog.notification.model.dto.request.BasePaginationReq;
import com.ntnt.highblog.notification.model.dto.response.NotificationRes;
import com.ntnt.highblog.notification.model.dto.response.NotificationSenderRes;
import com.ntnt.highblog.notification.model.dto.response.UserRes;
import com.ntnt.highblog.notification.model.entity.Notification;
import com.ntnt.highblog.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class NotificationListBloc {

    private final NotificationService notificationService;
    //    private final UserService userService;
    private final HbDmmClient hbDmmClient;

    public NotificationListBloc(final NotificationService notificationService,
                                final HbDmmClient hbDmmClient) {
        this.notificationService = notificationService;
        this.hbDmmClient = hbDmmClient;
    }

    public Page<NotificationRes> fetchNotificationsForCurrentUser(final BasePaginationReq basePaginationReq) {
        Long userId = SecurityHelper.getCurrentUserId();
        log.info("Fetch notification for current userId #{} with page info #{}", userId, basePaginationReq);

        PageRequest pageRequest = PaginationHelper.generatePageRequestWithDefaultSort(basePaginationReq,
                                                                                      "-id");

        Page<Notification> notifications = notificationService.fetchByReceiverIdWithPageRequest(userId, pageRequest);
        Page<NotificationRes> notificationsRes = notifications.map(NotificationMapper.INSTANCE::toNotificationRes);
        includeSenderIdToNotificationsRes(notificationsRes);

        return notificationsRes;
    }


    private void includeSenderIdToNotificationsRes(final Page<NotificationRes> notificationsRes) {
        Set<Long> senderIds = notificationsRes.map(NotificationRes::getSenderId).stream().collect(Collectors.toSet());

        ResponseEntity<List<UserRes>> responseEntity =
            hbDmmClient.fetchUsersByIdIn("Bearer " + SecurityHelper.getCurrentAccessToken(),
                                         new ArrayList<>(senderIds));

        if (responseEntity.getStatusCode().isError() || ObjectUtils.isEmpty(responseEntity.getBody())) {
            throw new ValidatorException("Error when get list user", "users");
        }

        Map<Long, List<UserRes>> senderIdsMap = responseEntity.getBody()
                                                              .stream()
                                                              .collect(Collectors.groupingBy(UserRes::getId));


        notificationsRes.forEach(
            notificationRes -> {
                UserRes userRes = senderIdsMap.get(notificationRes.getSenderId()).get(0);
                notificationRes.setSenderRes(NotificationSenderRes.builder()
                                                                  .imagePath(userRes.getImagePath())
                                                                  .nickName(userRes.getNickName())
                                                                  .build());
            });
    }
}
