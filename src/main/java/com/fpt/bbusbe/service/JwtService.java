package com.fpt.bbusbe.service;

import com.fpt.bbusbe.model.enums.TokenType;

import java.util.List;

public interface JwtService {

    String generateAccessToken(String phone, Long userId, List<String> authorities);

    String generateRefreshToken(String phone, Long userId, List<String> authorities);

    String extractPhone(String token, TokenType type);
}
