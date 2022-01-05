package com.ntnt.highblog.uaa.api.client;

import com.ntnt.highblog.uaa.model.dto.response.UserRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "hb-dmm")
public interface HbDmmClient {

    @GetMapping("/api/v1/profiles")
    ResponseEntity<UserRes> getProfileInfoForCurrentUser();
}
