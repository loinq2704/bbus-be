package com.fpt.bbusbe.service.impl;

import com.fpt.bbusbe.exception.ForBiddenException;
import com.fpt.bbusbe.exception.InvalidDataException;
import com.fpt.bbusbe.model.entity.User;
import com.fpt.bbusbe.model.dto.request.SignInRequest;
import com.fpt.bbusbe.model.dto.response.LoginResponse;
import com.fpt.bbusbe.model.dto.response.TokenResponse;
import com.fpt.bbusbe.repository.UserRepository;
import com.fpt.bbusbe.service.AuthenticationService;
import com.fpt.bbusbe.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.fpt.bbusbe.model.enums.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(SignInRequest request) {
        log.info("Get access token");

        List<String> authorities = new ArrayList<>();
        try {
            // Thực hiện xác thực với username và password
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword()));

            log.info("isAuthenticated = {}", authenticate.isAuthenticated());
            log.info("Authorities: {}", authenticate.getAuthorities().toString());
            authorities.add(authenticate.getAuthorities().toString());

            // Nếu xác thực thành công, lưu thông tin vào SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch (BadCredentialsException | DisabledException e) {
            log.error("errorMessage: {}", e.getMessage());
            throw new InternalAuthenticationServiceException(e.getMessage());
        }

        User user = userRepository.findByPhone(request.getPhone());

        String accessToken = jwtService.generateAccessToken(request.getPhone(), user.getId(), authorities);
        String refreshToken = jwtService.generateRefreshToken(request.getPhone(), user.getId(), authorities);
        String message = "Login successful";

        return LoginResponse.builder().access_token(accessToken).refresh_token(refreshToken).message(message).build();
    }

    @Override
    public TokenResponse getRefreshToken(String refreshToken) {
        log.info("Get refresh token");

        if (!StringUtils.hasLength(refreshToken)) {
            throw new InvalidDataException("Token must be not blank");
        }

        try {
            // Verify token
            String phone = jwtService.extractPhone(refreshToken, REFRESH_TOKEN);

            // check user is active or inactivated
            User user = userRepository.findByPhone(phone);
            List<String> authorities = new ArrayList<>();
            user.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));

            // generate new access token
            String accessToken = jwtService.generateAccessToken(user.getPhone(), user.getId(), authorities);

            return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (Exception e) {
            log.error("Access denied! errorMessage: {}", e.getMessage());
            throw new ForBiddenException(e.getMessage());
        }
    }
}
