package com.ntnt.highblog.notification.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomJwtGrantedAuthoritiesConverter
    implements Converter<Jwt, Collection<GrantedAuthority>> {
    private static final String DEFAULT_PREFIX_SCOPE_FOR_AUTHORITIES = "SCOPE_";

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.addAll(getScopeAuthorities(jwt));
        authorities.addAll(getRoleAuthorities(jwt));

        return authorities;
    }

    private List<GrantedAuthority> getScopeAuthorities(Jwt jwt) {
        List<String> scopes = jwt.getClaim("scope");

        if (Objects.nonNull(scopes)) {
            return scopes.stream()
                         .map(s -> new SimpleGrantedAuthority(DEFAULT_PREFIX_SCOPE_FOR_AUTHORITIES + s))
                         .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<GrantedAuthority> getRoleAuthorities(Jwt jwt) {
        List<String> roles = jwt.getClaim("role");

        if (Objects.nonNull(roles)) {
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
