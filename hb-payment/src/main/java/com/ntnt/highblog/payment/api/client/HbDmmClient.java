package com.ntnt.highblog.payment.api.client;

import com.ntnt.highblog.payment.model.dto.response.UserDetailRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hb-dmm")
public interface HbDmmClient {

    @GetMapping("/api/v1/internal/users/{nickname}")
    ResponseEntity<UserDetailRes> getUserDetailByNickname(@PathVariable String nickname);
}
