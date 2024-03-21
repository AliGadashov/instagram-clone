package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.request.UserChangePasswordRequest;
import com.gadashov.instagram.model.dto.request.UserLoginRequest;
import com.gadashov.instagram.model.dto.request.UserRegisterRequest;
import com.gadashov.instagram.model.dto.request.UserResetPasswordRequest;
import com.gadashov.instagram.model.dto.response.AuthResponse;
import com.gadashov.instagram.model.entity.User;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:16 PM
 */

public interface AuthService {

    void register(UserRegisterRequest request);

    AuthResponse login(UserLoginRequest request);

    void confirmation(String token);

    void forgetPassword(String username);

    void renewPassword(String otp, UserResetPasswordRequest request);

    void changePassword(User user, UserChangePasswordRequest request);
}