package com.ntnt.highblog.gateway.controller;

import com.ntnt.highblog.gateway.client.UaaClient;
import com.ntnt.highblog.gateway.dto.request.LoginReq;
import com.ntnt.highblog.gateway.dto.response.AuthorizationCodeGrantTypeRes;
import com.ntnt.highblog.gateway.dto.response.LoginRequestRes;
import com.ntnt.highblog.gateway.dto.response.LogoutRes;
import com.ntnt.highblog.gateway.dto.response.RefreshTokenGrantTypeRes;
import com.ntnt.highblog.gateway.helper.CookieHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;
    private final ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository;
    private final UaaClient uaaClient;

    public AuthController(final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository,
                          final ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository,
                          final ReactiveJwtDecoder reactiveJwtDecoder,
                          final UaaClient uaaClient) {
        this.reactiveClientRegistrationRepository = reactiveClientRegistrationRepository;
        this.serverOAuth2AuthorizedClientRepository = serverOAuth2AuthorizedClientRepository;
        this.uaaClient = uaaClient;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@CookieValue(CookieHelper.ACCESS_TOKEN_COOKIE_KEY)
                                                        String accessToken,
                                                @CookieValue(CookieHelper.REFRESH_TOKEN_TO_REFRESH_COOKIE_KEY)
                                                    String refreshToken,
                                                ServerWebExchange serverWebExchange) {
        Optional<RefreshTokenGrantTypeRes> refreshTokenGrantTypeResOpt =
            uaaClient.refreshAccessToken(accessToken, refreshToken);

        refreshTokenGrantTypeResOpt.ifPresent(refreshTokenGrantTypeRes -> {

            CookieHelper.setAccessTokenCookie(serverWebExchange.getResponse(),
                                              refreshTokenGrantTypeRes.getAccessToken());
            CookieHelper.setRefreshTokenCookie(serverWebExchange.getResponse(),
                                               refreshTokenGrantTypeRes.getRefreshToken());
        });

        if (refreshTokenGrantTypeResOpt.isPresent()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> getLoginUrl(@RequestParam final String redirectUri) {
        return ResponseEntity.ok(new LoginRequestRes(uaaClient.getLoginUrl(redirectUri)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq,
                                   ServerWebExchange serverWebExchange) {
        Optional<AuthorizationCodeGrantTypeRes> authorizationCodeGrantTypeResOpt =
            uaaClient.getToken(loginReq.getCode(), loginReq.getRedirectUri());

        authorizationCodeGrantTypeResOpt.ifPresent(authorizationCodeGrantTypeRes ->  {
            CookieHelper.setAccessTokenCookie(serverWebExchange.getResponse(),
                                              authorizationCodeGrantTypeRes.getAccessToken());
            CookieHelper.setRefreshTokenCookie(serverWebExchange.getResponse(),
                                               authorizationCodeGrantTypeRes.getRefreshToken());
        });

        if(authorizationCodeGrantTypeResOpt.isPresent()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .header("access-control-expose-headers", "Set-Cookie")
                             .build();
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RegisteredOAuth2AuthorizedClient(registrationId = "hb-gateway")
                                        OAuth2AuthorizedClient authorizedClient,
                                    ServerHttpRequest request,
                                    ServerHttpResponse response) {
        CookieHelper.clearTokenCookie(response);

        uaaClient.revokeToken(request.getCookies()
                                     .get(CookieHelper.ACCESS_TOKEN_COOKIE_KEY)
                                     .get(0)
                                     .getValue(),
                              "access_token");
        uaaClient.revokeToken(request.getCookies()
                                     .get(CookieHelper.REFRESH_TOKEN_TO_LOGOUT_COOKIE_KEY)
                                     .get(0)
                                     .getValue(),
                              "refresh_token");

        String stringBuilder =
            authorizedClient.getClientRegistration().getProviderDetails().getIssuerUri()
                + "/logout";
        return ResponseEntity.ok(new LogoutRes(stringBuilder));
    }
}
