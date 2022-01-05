package com.ntnt.highblog.gateway.helper;

import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public final class CookieHelper {
    public static final String ACCESS_TOKEN_COOKIE_KEY = "accessToken";
    public static final String REFRESH_TOKEN_COOKIE_KEY = "refreshToken";
    public static final String ACCESS_TOKEN_COOKIE_PATH = "/";
    public static final List<String> REFRESH_TOKEN_COOKIE_PATH = Arrays.asList("/api/v1/auth/refresh",
                                                                               "/api/v1/auth/logout");

    private CookieHelper() {
    }

    public static ResponseCookie getCookie(String name, String value, String path) {
        return ResponseCookie.from(name, value)
                             .path(path)
                             .httpOnly(true)
                             .secure(false)
                             .maxAge(Duration.ofSeconds(3600*24*7))
                             .build();
    }

    public static ResponseCookie getClearedCookie(String name, String path) {
        return ResponseCookie.from(name, "")
                             .path(path)
                             .httpOnly(true)
                             .secure(false)
                             .maxAge(Duration.ofSeconds(0))
                             .build();
    }

    public static void setAccessTokenCookie(ServerHttpResponse response, String value) {
        response.addCookie(getCookie(ACCESS_TOKEN_COOKIE_KEY,
                                     value,
                                     ACCESS_TOKEN_COOKIE_PATH));
    }

    public static void setRefreshTokenCookie(ServerHttpResponse response, String value) {
        REFRESH_TOKEN_COOKIE_PATH.forEach(path -> {
            response.addCookie(getCookie(REFRESH_TOKEN_COOKIE_KEY,
                                         value,
                                         path));
        });
    }

    public static void clearTokenCookie(ServerHttpResponse response) {
        response.addCookie(getClearedCookie(ACCESS_TOKEN_COOKIE_KEY,
                                            ACCESS_TOKEN_COOKIE_PATH));

        REFRESH_TOKEN_COOKIE_PATH.forEach(path -> {
            response.addCookie(getClearedCookie(REFRESH_TOKEN_COOKIE_KEY,
                                                path));
        });
    }
}
