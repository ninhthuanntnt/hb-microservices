package com.ntnt.highblog.dmm.helper;

import com.ntnt.highblog.dmm.error.exception.ObjectNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public final class SecurityHelper {
    private static final String DEFAULT_USER_ID_JWT_CLAIM = "userId";

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
