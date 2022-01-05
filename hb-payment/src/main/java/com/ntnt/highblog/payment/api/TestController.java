package com.ntnt.highblog.payment.api;

import com.ntnt.highblog.payment.api.client.HbNotificationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final HbNotificationClient notificationClient;

    public TestController(final HbNotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @GetMapping("/permit-all")
    public ResponseEntity<String> permitAll() {
        return notificationClient.permitAll();
    }
}
