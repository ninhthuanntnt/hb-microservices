package com.ntnt.highblog.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogoutRes {
    private String redirectUrl;
}
