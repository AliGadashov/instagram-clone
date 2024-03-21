package com.gadashov.instagram.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:02 PM
 */

public interface TokenService {

    String generateToken(String username);

    Claims getClaims(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
