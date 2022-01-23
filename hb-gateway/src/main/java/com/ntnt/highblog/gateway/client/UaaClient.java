package com.ntnt.highblog.gateway.client;

import com.ntnt.highblog.gateway.dto.response.AuthorizationCodeGrantTypeRes;
import com.ntnt.highblog.gateway.dto.response.RefreshTokenGrantTypeRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Component
public class UaaClient {
    private final RestTemplate restTemplate;
    private final OAuth2ClientProperties.Registration clientRegistration;
    private final OAuth2ClientProperties oAuth2ClientProperties;

    public UaaClient(final OAuth2ClientProperties oAuth2ClientProperties) {
        this.clientRegistration = oAuth2ClientProperties.getRegistration().get("hb-gateway");
        this.oAuth2ClientProperties = oAuth2ClientProperties;
        this.restTemplate = new RestTemplateBuilder().basicAuthentication(clientRegistration.getClientId(),
                                                                          clientRegistration.getClientSecret())
                                                     .build();
    }

    public Optional<RefreshTokenGrantTypeRes> refreshAccessToken(final String accessToken, final String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("refresh_token", refreshToken);
            form.add("grant_type", "refresh_token");
            form.add("scope", "openid");
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);
            ResponseEntity<RefreshTokenGrantTypeRes> responseEntity =
                restTemplate.postForEntity(oAuth2ClientProperties.getProvider()
                                                                 .get("hb-uaa")
                                                                 .getTokenUri(),
                                           httpEntity,
                                           RefreshTokenGrantTypeRes.class);

            return Optional.ofNullable(responseEntity.getBody());
        } catch (Exception e) {
            log.error("Cannot refresh token by #{}", e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<AuthorizationCodeGrantTypeRes> getToken(final String code, final String redirectUri) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("client_id", clientRegistration.getClientId());
            form.add("grant_type", "authorization_code");
            form.add("scope", "openid");
            form.add("redirect_uri", redirectUri);
            form.add("code", code);
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);
            ResponseEntity<AuthorizationCodeGrantTypeRes> responseEntity =
                restTemplate.postForEntity(oAuth2ClientProperties.getProvider()
                                                                 .get("hb-uaa")
                                                                 .getTokenUri(),
                                           httpEntity,
                                           AuthorizationCodeGrantTypeRes.class);

            return Optional.ofNullable(responseEntity.getBody());
        } catch (Exception e) {
            log.error("Cannot get token by #{}", e.getMessage());
            return Optional.empty();
        }
    }

    public String getLoginUrl(final String redirectUri) {
        UriComponentsBuilder uriComponentsBuilder =
            UriComponentsBuilder.fromUriString(oAuth2ClientProperties.getProvider()
                                                                     .get("hb-uaa")
                                                                     .getAuthorizationUri())
                                .queryParam("response_type", "code")
                                .queryParam("scope", "openid")
                                .queryParam("client_id", clientRegistration.getClientId())
                                .queryParam("redirect_uri", redirectUri);

        return uriComponentsBuilder.toUriString();
    }

    public void revokeToken(final String token, final String tokenType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("type", tokenType);
            form.add("token", token);
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);
            restTemplate.postForEntity(oAuth2ClientProperties.getProvider()
                                                             .get("hb-uaa")
                                                             .getIssuerUri() + "/oauth2/revoke",
                                       httpEntity,
                                       RefreshTokenGrantTypeRes.class);

        } catch (Exception e) {
            log.error("Cannot revoke token by #{}", e.getMessage());
        }
    }
}
