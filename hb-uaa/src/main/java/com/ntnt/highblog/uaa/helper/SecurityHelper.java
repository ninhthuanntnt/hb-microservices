package com.ntnt.highblog.uaa.helper;

import com.ntnt.highblog.uaa.error.exception.ObjectNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public final class SecurityHelper {
    public static final String DEFAULT_USER_ID_JWT_CLAIM = "userId";
    public static final String DEFAULT_ACCOUNT_ID_JWT_CLAIM = "accountId";
    public static final String DEFAULT_ROLE_JWT_CLAIM = "roles";

    private SecurityHelper() {
    }

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return jwt.getClaim(DEFAULT_USER_ID_JWT_CLAIM);
        }

        throw new ObjectNotFoundException(DEFAULT_USER_ID_JWT_CLAIM);
    }

    public static Long getCurrentAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return jwt.getClaim(DEFAULT_ACCOUNT_ID_JWT_CLAIM);
        }

        throw new ObjectNotFoundException(DEFAULT_ACCOUNT_ID_JWT_CLAIM);
    }

    public static Optional<Long> getNullableCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return Optional.ofNullable(jwt.getClaim(DEFAULT_USER_ID_JWT_CLAIM));
        }

        return Optional.empty();
    }

    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return jwt.getSubject();
        }

        throw new ObjectNotFoundException(DEFAULT_USER_ID_JWT_CLAIM);
    }

    public static Optional<String> getNullableCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return Optional.ofNullable(jwt.getSubject());
        }

        return Optional.empty();
    }
}
