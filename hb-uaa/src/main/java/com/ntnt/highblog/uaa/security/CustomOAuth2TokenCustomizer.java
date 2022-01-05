package com.ntnt.highblog.uaa.security;

import com.ntnt.highblog.uaa.helper.SecurityHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomOAuth2TokenCustomizer
    implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(final JwtEncodingContext context) {
        Authentication authentication = context.getPrincipal();
        Set<String> authorities = authentication.getAuthorities().stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toSet());
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        context.getClaims().claim(SecurityHelper.DEFAULT_ACCOUNT_ID_JWT_CLAIM, customUserDetails.getId());
        context.getClaims().claim(SecurityHelper.DEFAULT_USER_ID_JWT_CLAIM, customUserDetails.getUserId());
        context.getClaims().claim(SecurityHelper.DEFAULT_ROLE_JWT_CLAIM, authorities);
    }
}
