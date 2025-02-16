package com.fpt.bbusbe.controller;

import com.fpt.bbusbe.model.request.SignInRequest;
import com.fpt.bbusbe.model.response.LoginResponse;
import com.fpt.bbusbe.model.response.TokenResponse;
import com.fpt.bbusbe.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-CONTROLLER")
@Tag(name = "Authentication Controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

//    @Operation(summary = "Access Token", description = "Get access token and refresh token by username and password")
//    @PostMapping("access-token")
//    public TokenResponse getAccessToken(@RequestBody SignInRequest signInRequest) {
//        log.info("Access token request...");
//
//        return authenticationService.getAccessToken(signInRequest);
//    }

    @Operation(summary = "Login", description = "Get access token and refresh token by username and password")
    @PostMapping("login")
    public LoginResponse getAccessToken(@RequestBody SignInRequest signInRequest) {
        log.info("Login request...");

        return authenticationService.login(signInRequest);
    }

    @Operation(summary = "Refresh Token", description = "Get new access token by refresh token")
    @PostMapping("refresh-token")
    public TokenResponse getRefreshToken(@RequestBody String refreshToken) {
        log.info("Refresh token request...");

        return authenticationService.getRefreshToken(refreshToken);
    }

}
