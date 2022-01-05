package com.ntnt.highblog.gateway.security;

import com.ntnt.highblog.gateway.config.ApplicationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class CustomAuthenticationSuccessHandler
    extends RedirectServerAuthenticationSuccessHandler {
    private final ServerOAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
    private final ApplicationProperties.Uri appUri;

    public CustomAuthenticationSuccessHandler(final ServerOAuth2AuthorizedClientRepository auth2AuthorizedClientRepository,
                                              final ApplicationProperties applicationProperties) {
        this.auth2AuthorizedClientRepository = auth2AuthorizedClientRepository;
        this.appUri = applicationProperties.getUri();
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(final WebFilterExchange webFilterExchange, final Authentication authentication) {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        return
            auth2AuthorizedClientRepository
                .loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                                      authentication,
                                      webFilterExchange.getExchange())
                .flatMap(oAuth2AuthorizedClient ->
                             Mono.fromRunnable(
                                 () -> {
                                     ServerHttpResponse serverHttpResponse = webFilterExchange.getExchange()
                                                                                              .getResponse();
                                     serverHttpResponse.setStatusCode(HttpStatus.FOUND);
                                     serverHttpResponse.getHeaders().setLocation(URI.create(appUri.getFrontEndUser()));
                                 }
                             )
                );
    }
}
