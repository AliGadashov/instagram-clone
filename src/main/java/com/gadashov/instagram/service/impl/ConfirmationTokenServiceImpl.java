package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.model.entity.ConfirmationToken;
import com.gadashov.instagram.model.enums.ConfirmationType;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.ConfirmationTokenRepository;
import com.gadashov.instagram.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:43 PM
 */

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository repository;

    @Override
    public ConfirmationToken findByCode(String otp, ConfirmationType type) {
        ConfirmationToken confirmationToken = repository.findByTokenAndType(otp, type)
                .orElseThrow(() -> new GlobalException(Exceptions.OPT_NOT_FOUND));
        return confirmationToken;
    }
}
