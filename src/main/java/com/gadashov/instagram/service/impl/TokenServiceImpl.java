package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:03 PM
 */

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.key}")
    private String key;
    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + (7200 * 1000)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey())
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        Date expireDate = claims.getExpiration();
        return userDetails.getUsername().equals(username) && !expireDate.before(new Date());
    }

    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
