package com.gadashov.instagram.service.impl;


import com.gadashov.instagram.mapper.UserMapper;
import com.gadashov.instagram.model.dto.request.UserChangePasswordRequest;
import com.gadashov.instagram.model.dto.request.UserLoginRequest;
import com.gadashov.instagram.model.dto.request.UserRegisterRequest;
import com.gadashov.instagram.model.dto.request.UserResetPasswordRequest;
import com.gadashov.instagram.model.dto.response.AuthResponse;
import com.gadashov.instagram.model.entity.ConfirmationToken;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.ConfirmationType;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.UserRepository;
import com.gadashov.instagram.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:17 PM
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final MailService mailService;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public void register(UserRegisterRequest request) {
        if (repository.findByUsernameAndEmail(request.getUsername(), request.getEmail()).isPresent())
            throw new GlobalException(Exceptions.USERNAME_OR_EMAIL_EXISTS);

        String key = UUID.randomUUID().toString();

        String hash = UUID.randomUUID().toString();
        String password = request.getPassword() + hash;
        String encodedPassword = passwordEncoder.encode(password);
        User user = mapper.toEntity(request);
        user.setPassword(encodedPassword);
        user.setPasswordHash(hash);

        ConfirmationToken token = ConfirmationToken.builder()
                .token(key)
                .user(user)
                .expireTime(LocalDateTime.now().plusMonths(1))
                .type(ConfirmationType.ACTIVATION)
                .build();

        user.setConfirmationToken(token);


        String content = "<a href=\"http://localhost:8080/api/v1/auth/confirmation?token=%s\".> click to activate</a>".formatted(key);
        mailService.sendMessage(user.getEmail(), "Eshop activate user", content);
        repository.save(user);
    }

    @Override
    public AuthResponse login(UserLoginRequest request) {
        String username = request.getUsername();
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new GlobalException(Exceptions.USER_NOT_FOUND));

        String password = request.getPassword() + user.getPasswordHash();

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new GlobalException(Exceptions.USER_INVALID_PASSWORD);

        if (!user.isEnabled()) {
            throw new GlobalException(Exceptions.USER_NOT_ACTIVATED);
        }

        if (!user.isAccountNonLocked()) {
            throw new GlobalException(Exceptions.USER_IS_LOCKED);
        }
        String accessToken = tokenService.generateToken(user.getUsername());

        return new AuthResponse(accessToken);
    }

    @Override
    public void confirmation(String otp) {
        User user = confirmationTokenService.findByCode(otp, ConfirmationType.ACTIVATION).getUser();
        user.setEnabled(true);
        user.getConfirmationToken().setToken("");
        repository.save(user);
    }

    @Override
    public void forgetPassword(String username) {
        User user = (User) userService.loadUserByUsername(username);
        String email = user.getEmail();
        String otp = UUID.randomUUID().toString();

        ConfirmationToken token = ConfirmationToken.builder()
                .token(otp)
                .expireTime(LocalDateTime.now().plusMinutes(3))
                .type(ConfirmationType.RESET_PASSWORD).build();
        user.setConfirmationToken(token);
        repository.save(user);
        mailService.sendMessage(email, "User forget password Request", otp);
    }

    @Override
    public void renewPassword(String otp, UserResetPasswordRequest request) {
        User user = confirmationTokenService.findByCode(otp, ConfirmationType.RESET_PASSWORD).getUser();

        if (!request.getNewPassword().equals(request.getNewPasswordRepeat())) {
            throw new GlobalException(Exceptions.USER_MISMATCH_PASSWORD);
        }
        String hash = user.getPasswordHash();
        String newPassword = request.getNewPassword() + hash;
        String encodeNewPassword = passwordEncoder.encode(newPassword);

        if(user.getPassword().equals(encodeNewPassword)) {
            throw new GlobalException(Exceptions.CURRENT_PASSWORD_NEW_PASSWORD);
        }
        user.getConfirmationToken().setToken("");
        user.setPassword(encodeNewPassword);
        repository.save(user);
    }


    @Override
    public void changePassword(User user, UserChangePasswordRequest request) {
        String hash = user.getPasswordHash();
        String password = request.getCurrentPassword() + hash;

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new GlobalException(Exceptions.USER_INVALID_PASSWORD);

        if (!request.getNewPassword().equals(request.getNewPasswordRepeat())) {
            throw new GlobalException(Exceptions.USER_MISMATCH_PASSWORD);
        }
        String newPassword = request.getNewPassword() + hash;
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodeNewPassword);
        repository.save(user);
    }
}
