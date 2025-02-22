package com.fpt.bbusbe.service;


import com.fpt.bbusbe.model.dto.request.SignInRequest;
import com.fpt.bbusbe.model.dto.response.LoginResponse;
import com.fpt.bbusbe.model.dto.response.TokenResponse;

public interface AuthenticationService {

    LoginResponse login(SignInRequest signInRequest);
    TokenResponse getRefreshToken(String refreshToken);

}
