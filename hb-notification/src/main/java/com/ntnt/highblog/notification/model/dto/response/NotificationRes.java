package com.ntnt.highblog.notification.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntnt.highblog.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationRes {

    private Long id;
    private Long senderId;
    private Long sourceId;
    private String content;
    private NotificationType type;

    @JsonProperty("sender")
    private NotificationSenderRes senderRes;
}
