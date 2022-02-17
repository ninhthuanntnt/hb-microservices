package com.ntnt.highblog.notification.api.external;

import com.ntnt.highblog.notification.bloc.NotificationListBloc;
import com.ntnt.highblog.notification.helper.PaginationHelper;
import com.ntnt.highblog.notification.model.dto.request.BasePaginationReq;
import com.ntnt.highblog.notification.model.dto.response.NotificationRes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userNotificationListController")
@RequestMapping("/api/v1/user/notifications")
public class NotificationListController {

    private final NotificationListBloc notificationListBloc;

    public NotificationListController(final NotificationListBloc notificationListBloc) {
        this.notificationListBloc = notificationListBloc;
    }

    @GetMapping
    public ResponseEntity<?> fetchNotifications(final BasePaginationReq basePaginationReq) {
        Page<NotificationRes> notifications = notificationListBloc.fetchNotificationsForCurrentUser(basePaginationReq);
        return ResponseEntity.ok(PaginationHelper.buildBasePaginationRes(notifications));
    }
}
