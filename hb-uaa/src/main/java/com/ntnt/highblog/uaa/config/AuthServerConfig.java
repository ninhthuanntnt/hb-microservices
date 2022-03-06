package com.ntnt.highblog.uaa.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
public class AuthServerConfig {
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final String hbUaaUrl;

    public AuthServerConfig(final PasswordEncoder passwordEncoder,
                            final JwtAuthenticationConverter jwtAuthenticationConverter,
                            @Value("${application.hb-uaa-url}") String hbUaaUrl) {
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
        this.hbUaaUrl = hbUaaUrl;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
        throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        return http.formLogin(Customizer.withDefaults())
                   .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter)
                   .and()
                   .and()
                   .build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient1 =
            RegisteredClient.withId(UUID.randomUUID().toString())
                            .clientId("hb_client_id_1")
                            .clientSecret(passwordEncoder.encode("hb_client_secret_1"))
                            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                            .redirectUris(redirectUris -> {
                                redirectUris.add("https://oauth.pstmn.io/v1/callback");
                                redirectUris.add("http://127.0.0.1:8080/login/oauth2/code/hb-gateway");
                                redirectUris.add("http://159.223.75.208:8080/login/oauth2/code/hb-gateway");
                                redirectUris.add("http://localhost:8080/authorized");
                                redirectUris.add("http://127.0.0.1:3000/login");
                                redirectUris.add("http://localhost:3000/login");
                                redirectUris.add("http://159.223.75.208:3000/login");
                            })
                            .scope(OidcScopes.OPENID)
                            .scope("notification.read")
                            .tokenSettings(TokenSettings.builder()
                                                        .refreshTokenTimeToLive(Duration.ofDays(7))
                                                        .reuseRefreshTokens(false)
                                                        .accessTokenTimeToLive(Duration.ofSeconds(3600))
                                                        .build())
                            .build();

        return new InMemoryRegisteredClientRepository(registeredClient1);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource()
        throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);

        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa()
        throws NoSuchAlgorithmException {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey).privateKey(privateKey)
                                            .keyID(UUID.randomUUID().toString())
                                            .build();
    }

    private static KeyPair generateRsaKey()
        throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                               .issuer(hbUaaUrl)
                               .build();
    }
}
