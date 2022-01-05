package com.ntnt.highblog.uaa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class CustomLogoutSuccessHandler
    implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication)
        throws IOException, ServletException {
        String redirectUrl = request.getParameter("redirectUrl");

        if (Objects.nonNull(redirectUrl)) {
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect("/login");
        }
    }
}
