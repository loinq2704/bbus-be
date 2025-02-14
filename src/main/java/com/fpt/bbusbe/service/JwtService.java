package com.fpt.bbusbe.service;

import com.fpt.bbusbe.model.enums.TokenType;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtService {

    String generateAccessToken(long userId, String username, Collection<? extends GrantedAuthority> authorities);

    String generateRefreshToken(long userId, String username, Collection<? extends GrantedAuthority> authorities);

    String extractUsername(String token, TokenType type);
}
