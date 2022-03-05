package com.ntnt.highblog.dmm.api.client;

import com.ntnt.highblog.dmm.model.request.NotificationCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
@FeignClient(name = "hb-notification")
public interface HbNotificationClient {

    @PostMapping("/api/v1/internal/notifications")
    ResponseEntity<Void> createNotification(@RequestBody @Valid NotificationCreateReq notificationCreateReq);
}
