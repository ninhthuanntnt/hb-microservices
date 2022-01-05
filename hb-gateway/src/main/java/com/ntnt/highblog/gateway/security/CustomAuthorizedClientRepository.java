package com.ntnt.highblog.gateway.security;

import com.ntnt.highblog.gateway.helper.CookieHelper;
import org.springframework.http.HttpCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component("serverOAuth2AuthorizedClientRepository")
public class CustomAuthorizedClientRepository
    implements ServerOAuth2AuthorizedClientRepository {

    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    public CustomAuthorizedClientRepository(final ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(final String clientRegistrationId,
                                                                           final Authentication principal,
                                                                           final ServerWebExchange exchange) {
        HttpCookie accessTokenCookie = exchange.getRequest().getCookies().getFirst("accessToken");
        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                                                                    "accessToken",
                                                                    Instant.now(),
                                                                    Instant.now().plus(1, ChronoUnit.DAYS));
        String principalName = "";
        if (accessTokenCookie == null) {
            accessTokenCookie = exchange.getResponse().getCookies().getFirst("accessToken");
        }
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
            oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                                                      accessTokenCookie.getValue(),
                                                      defaultOidcUser.getIssuedAt(),
                                                      defaultOidcUser.getExpiresAt());
            principalName = defaultOidcUser.getName();
        } else if (principal instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
            oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                                                      jwtAuthenticationToken.getToken().getTokenValue(),
                                                      jwtAuthenticationToken.getToken().getIssuedAt(),
                                                      jwtAuthenticationToken.getToken().getExpiresAt());
            principalName = jwtAuthenticationToken.getName();
        }


        OAuth2AccessToken finalOAuth2AccessToken = oAuth2AccessToken;
        String finalPrincipalName = principalName;
        return clientRegistrationRepository.findByRegistrationId(clientRegistrationId)
                                           .map(clientRegistration -> new OAuth2AuthorizedClient(clientRegistration,
                                                                                                 finalPrincipalName,
                                                                                                 finalOAuth2AccessToken))
                                           .flatMap(oAuth2AuthorizedClient -> Mono.just((T) oAuth2AuthorizedClient));
    }

    @Override
    public Mono<Void> saveAuthorizedClient(final OAuth2AuthorizedClient authorizedClient,
                                           final Authentication principal,
                                           final ServerWebExchange exchange) {
        CookieHelper.setAccessTokenCookie(exchange.getResponse(),
                                          authorizedClient.getAccessToken().getTokenValue());

        if (authorizedClient.getRefreshToken() != null) {
            CookieHelper.setRefreshTokenCookie(exchange.getResponse(),
                                               authorizedClient.getRefreshToken().getTokenValue());
        }
        return Mono.empty();
    }

    @Override
    public Mono<Void> removeAuthorizedClient(final String clientRegistrationId, final Authentication principal, final ServerWebExchange exchange) {
        return null;
    }
}
