package com.ntnt.highblog.gateway.helper;

import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.time.Duration;

public final class CookieHelper {
    public static final String ACCESS_TOKEN_COOKIE_KEY = "accessToken";
    public static final String REFRESH_TOKEN_TO_REFRESH_COOKIE_KEY = "refreshToken-refresh";
    public static final String REFRESH_TOKEN_TO_LOGOUT_COOKIE_KEY = "refreshToken-logout";
    public static final String ACCESS_TOKEN_COOKIE_PATH = "/";
    public static final String REFRESH_TOKEN_COOKIE_PATH_REFRESH = "/api/v1/auth/refresh";
    public static final String REFRESH_TOKEN_COOKIE_PATH_LOGOUT = "/api/v1/auth/logout";

    private CookieHelper() {
    }

    public static ResponseCookie getCookie(String name, String value, String path) {
        return ResponseCookie.from(name, value)
                             .path(path)
                             .httpOnly(true)
                             .domain("192.168.1.105")
                             .maxAge(Duration.ofSeconds(3600*24*7))
                             .build();
    }

    public static ResponseCookie getClearedCookie(String name, String path) {
        return ResponseCookie.from(name, "")
                             .path(path)
                             .domain("192.168.1.105")
                             .httpOnly(true)
                             .maxAge(Duration.ofSeconds(0))
                             .build();
    }

    public static void setAccessTokenCookie(ServerHttpResponse response, String value) {
        response.addCookie(getCookie(ACCESS_TOKEN_COOKIE_KEY,
                                     value,
                                     ACCESS_TOKEN_COOKIE_PATH));
    }

    public static void setRefreshTokenCookie(ServerHttpResponse response, String value) {
        response.addCookie(getCookie(REFRESH_TOKEN_TO_REFRESH_COOKIE_KEY,
                                     value,
                                     REFRESH_TOKEN_COOKIE_PATH_REFRESH));
        response.addCookie(getCookie(REFRESH_TOKEN_TO_LOGOUT_COOKIE_KEY,
                                     value,
                                     REFRESH_TOKEN_COOKIE_PATH_LOGOUT));
    }

    public static void clearTokenCookie(ServerHttpResponse response) {
        response.addCookie(getClearedCookie(ACCESS_TOKEN_COOKIE_KEY,
                                            ACCESS_TOKEN_COOKIE_PATH));
        response.addCookie(getClearedCookie(REFRESH_TOKEN_TO_REFRESH_COOKIE_KEY,
                                     REFRESH_TOKEN_COOKIE_PATH_REFRESH));
        response.addCookie(getClearedCookie(REFRESH_TOKEN_TO_LOGOUT_COOKIE_KEY,
                                     REFRESH_TOKEN_COOKIE_PATH_LOGOUT));
    }
}
