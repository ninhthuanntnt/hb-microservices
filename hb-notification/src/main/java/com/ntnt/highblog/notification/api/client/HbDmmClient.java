package com.ntnt.highblog.notification.api.client;

import com.ntnt.highblog.notification.model.dto.response.SubscriptionRes;
import com.ntnt.highblog.notification.model.dto.response.UserRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "hb-dmm")
public interface HbDmmClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/internal/users", params = "ids")
    ResponseEntity<List<UserRes>> fetchUsersByIdIn(@RequestHeader(value = "Authorization")
                                                       String bearerToken,
                                                   @RequestParam List<Long> ids);

    @GetMapping(value = "/api/v1/internal/subscriptions", params = "userId")
    ResponseEntity<List<SubscriptionRes>> fetchSubscriptionsByUserId(Long userId);
}
