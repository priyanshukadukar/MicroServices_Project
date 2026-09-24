package com.gateway.ApiGateway.controller;


import com.gateway.ApiGateway.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user
    ) {
        logger.info("User email ID: {}", user != null ? user.getEmail() : "No user email");
        AuthResponse authResponse = new AuthResponse();

        if (user != null) {
            authResponse.setUserId(user.getEmail());

            List<String> authorities = user.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .collect(Collectors.toList());
            authResponse.setAuthorities(authorities);
        }

        if (client != null && client.getAccessToken() != null) {
            authResponse.setAccessToken(client.getAccessToken().getTokenValue());

            if (client.getAccessToken().getExpiresAt() != null) {
                authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
            }

            if (client.getRefreshToken() != null) {
                authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
            }
        }

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}