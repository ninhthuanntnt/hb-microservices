package com.ntnt.highblog.notification.security;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class AuthChannelInterceptorAdapter
    implements ChannelInterceptor {

    private final JwtDecoder jwtDecoder;

    private static final String AUTH_HEADER = "Authorization";

    public AuthChannelInterceptorAdapter(final JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        final Authentication authentication = (Authentication) accessor.getSessionAttributes().get("authentication");

        if(accessor.getCommand() == StompCommand.CONNECT) {
            accessor.setLeaveMutable(false);
            accessor.setUser(authentication);
        }
        return message;
    }

    @Override
    public Message<?> postReceive(final Message<?> message, final MessageChannel channel) {
        return ChannelInterceptor.super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(final Message<?> message, final MessageChannel channel, final Exception ex) {
        ChannelInterceptor.super.afterReceiveCompletion(message, channel, ex);
    }
}
