package com.userServiceAuth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OktaAuthController {

    @GetMapping("/user")
    public Map<String, Object> getUserDetails(@AuthenticationPrincipal OidcUser user) {
        return user.getClaims();
    }
}
