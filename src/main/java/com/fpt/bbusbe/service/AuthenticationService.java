package com.fpt.bbusbe.service;


import com.fpt.bbusbe.model.request.SignInRequest;
import com.fpt.bbusbe.model.response.LoginResponse;
import com.fpt.bbusbe.model.response.TokenResponse;

public interface AuthenticationService {

    LoginResponse login(SignInRequest signInRequest);
    TokenResponse getRefreshToken(String refreshToken);

}
