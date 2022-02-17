package com.ntnt.highblog.notification.api.internal;

import com.ntnt.highblog.notification.bloc.NotificationBloc;
import com.ntnt.highblog.notification.model.dto.request.NotificationCreateReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("internalNotificationCrudController")
@RequestMapping("/api/v1/internal/notifications")
public class NotificationCrudController {
    private final NotificationBloc notificationBloc;

    public NotificationCrudController(final NotificationBloc notificationBloc) {
        this.notificationBloc = notificationBloc;
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@Valid NotificationCreateReq notificationCreateReq) {

    }
}
