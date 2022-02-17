package com.ntnt.highblog.notification.api.external;

import com.ntnt.highblog.notification.bloc.NotificationBloc;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller("websocketNotificationController")
public class NotificationController {
    private final NotificationBloc notificationBloc;


    public NotificationController(final NotificationBloc notificationBloc) {
        this.notificationBloc = notificationBloc;
    }

    @SubscribeMapping("/exchange/amq.direct/notification")
    public void sendInitNotification() {
        notificationBloc.pushUnreadNotificationToCurrentUser();
    }

    @MessageMapping("/notification.setSent.{notificationId}")
    public void markSentForNotification(@DestinationVariable("notificationId") Long notificationId) {
        notificationBloc.markSentNotificationForCurrentUser(notificationId);
    }
}
