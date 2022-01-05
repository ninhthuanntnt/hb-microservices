package com.ntnt.highblog.payment.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "hb-notification")
public interface HbNotificationClient {

    @GetMapping("/test/permit-all")
    ResponseEntity<String> permitAll();
}
