package com.ntnt.highblog.gateway.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
    private final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;
    private final ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository;
    private final ReactiveOAuth2AuthorizedClientManager reactiveOAuth2AuthorizedClientProvider;
    private final ReactiveJwtDecoder reactiveJwtDecoder;
    private final Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter;

    public TestController(final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository,
                          final ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository,
                          final ReactiveOAuth2AuthorizedClientManager ReactiveOAuth2AuthorizedClientManager,
                          final ReactiveJwtDecoder reactiveJwtDecoder) {
        this.reactiveClientRegistrationRepository = reactiveClientRegistrationRepository;
        this.serverOAuth2AuthorizedClientRepository = serverOAuth2AuthorizedClientRepository;
        this.reactiveOAuth2AuthorizedClientProvider = ReactiveOAuth2AuthorizedClientManager;
        this.reactiveJwtDecoder = reactiveJwtDecoder;
        this.jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverterAdapter(new JwtAuthenticationConverter());
    }

    @GetMapping(value = "/test/token")
    public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient(registrationId = "hb-gateway") OAuth2AuthorizedClient authorizedClient,
                                Authentication authentication,
                                ServerWebExchange serverWebExchange) {
        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
    }

    @GetMapping("/refresh")
    private Mono<?> refreshAccessToken(@RequestParam String refreshToken,
                                       Authentication authentication,
                                       @CookieValue("accessToken") String accessToken) {
        return reactiveJwtDecoder.decode(accessToken)
                                 .flatMap(this.jwtAuthenticationConverter::convert)
                                 .doOnError(throwable -> {
                                     if (throwable instanceof JwtValidationException) {
                                         JwtValidationException e = (JwtValidationException) throwable;
                                         e.getErrors().forEach(oAuth2Error -> {
                                             System.out.println(oAuth2Error.getErrorCode());
                                         });
                                     }
                                 })
                                 .log();
    }

    @GetMapping("/test")
    public Mono<String> index(WebSession session) {
        return Mono.just(session.getId());
    }
}
