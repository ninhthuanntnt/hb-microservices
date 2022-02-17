package com.ntnt.highblog.notification.security;

import com.ntnt.highblog.notification.helper.CookieHelper;
import liquibase.pro.packaged.J;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.Map;

@Component
public class AuthHandshakeInterceptorAdapter
    implements HandshakeInterceptor {

    private final JwtDecoder jwtDecoder;

    public AuthHandshakeInterceptorAdapter(final JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public boolean beforeHandshake(final ServerHttpRequest request,
                                   final ServerHttpResponse response,
                                   final WebSocketHandler wsHandler,
                                   final Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerRequest = (ServletServerHttpRequest) request;
            HttpServletRequest servletRequest = servletServerRequest.getServletRequest();
            Cookie accessTokenCookie = WebUtils.getCookie(servletRequest, CookieHelper.ACCESS_TOKEN_COOKIE_KEY);

            if (ObjectUtils.isNotEmpty(accessTokenCookie) && ObjectUtils.isNotEmpty(accessTokenCookie.getValue())) {
                Jwt jwt = jwtDecoder.decode(accessTokenCookie.getValue());
                JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt, Collections.EMPTY_LIST);
//                attributes.put("authentication",
//                               new UsernamePasswordAuthenticationToken(CustomUserDetails.builder()
//                                                                                        .userId(jwt.getClaim("userId"))
//                                                                                        .username(jwt.getSubject())
//                                                                                        .build(),
//                                                                       null,
//                                                                       Collections.emptyList()));
                attributes.put("authentication", jwtAuthenticationToken);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(final ServerHttpRequest request,
                               final ServerHttpResponse response,
                               final WebSocketHandler wsHandler,
                               final Exception exception) {
        System.out.println("ABC");
    }
}
