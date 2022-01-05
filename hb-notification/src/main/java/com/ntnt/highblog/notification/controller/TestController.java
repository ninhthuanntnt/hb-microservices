package com.ntnt.highblog.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/permit-all")
    public ResponseEntity<String> permitAll() {
        return ResponseEntity.ok("permit-all");
    }

    @GetMapping("/authenticated")
    public ResponseEntity<String> authenticated(HttpServletRequest servletRequest,
                                                final Authentication authentication) {
        return ResponseEntity.ok("authenticated");
    }
}