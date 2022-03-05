package com.ntnt.highblog.uaa.api.client;

import com.ntnt.highblog.uaa.model.dto.request.RegisterReq;
import com.ntnt.highblog.uaa.model.dto.response.UserRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hb-dmm")
public interface HbDmmClient {

    @GetMapping("/api/v1/profiles")
    ResponseEntity<UserRes> getProfileInfoForCurrentUser();

//    @GetMapping(value = "/api/v1/internal/users", params = "accountId")
//    ResponseEntity<UserRes> getByAccountId(@RequestParam Long accountId);

    @PostMapping(value = "/api/v1/internal/users")
    ResponseEntity<UserRes> createUser(@RequestBody RegisterReq registerReq);
}
