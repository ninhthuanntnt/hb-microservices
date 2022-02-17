package com.ntnt.highblog.notification.config;

import com.ntnt.highblog.notification.security.AuthHandshakeInterceptorAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebsocketConfig
    implements WebSocketMessageBrokerConfigurer {

    private final ApplicationConfigProperties.Cors corsConfigProperties;
    private final ApplicationConfigProperties.RabbitMq rabbitMqConfigProperties;
    private final AuthHandshakeInterceptorAdapter authHandshakeInterceptorAdapter;

    public WebsocketConfig(final ApplicationConfigProperties applicationConfigProperties,
                           final AuthHandshakeInterceptorAdapter authHandshakeInterceptorAdapter) {
        this.corsConfigProperties = applicationConfigProperties.getCors();
        this.rabbitMqConfigProperties = applicationConfigProperties.getRabbitMq();
        this.authHandshakeInterceptorAdapter = authHandshakeInterceptorAdapter;
    }


    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/queue", "/exchange")
                .setRelayHost(rabbitMqConfigProperties.getHost())
                .setRelayPort(rabbitMqConfigProperties.getPort())
                .setClientLogin(rabbitMqConfigProperties.getUsername())
                .setClientPasscode(rabbitMqConfigProperties.getPassword())
                .setSystemLogin(rabbitMqConfigProperties.getUsername())
                .setSystemPasscode(rabbitMqConfigProperties.getPassword());

        registry.setApplicationDestinationPrefixes("/app", "/user");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/notification").setAllowedOriginPatterns("*");
        registry.addEndpoint("/notification").setAllowedOriginPatterns("*")
                .withSockJS()
                .setTransportHandlers()
                .setInterceptors(authHandshakeInterceptorAdapter);
    }

}
