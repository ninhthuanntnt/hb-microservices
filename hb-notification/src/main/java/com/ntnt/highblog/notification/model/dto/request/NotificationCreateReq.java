package com.ntnt.highblog.notification.model.dto.request;

import com.ntnt.highblog.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationCreateReq {

    @NotNull
    private String content;

    private Long sourceId;

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private NotificationSenderReq notificationSenderReq;

    @NotNull
    private List<Long> receiverIds;
}
