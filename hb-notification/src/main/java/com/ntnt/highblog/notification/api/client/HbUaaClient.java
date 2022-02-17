package com.ntnt.highblog.notification.api.client;

import com.ntnt.highblog.notification.model.dto.response.AccountRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(name = "hb-uaa")
public interface HbUaaClient {

    @GetMapping(value = "/api/v1/internal/accounts", params = "userIds")
    ResponseEntity<List<AccountRes>> fetchAccountsByUserIdIn(List<Long> userIds);
}
