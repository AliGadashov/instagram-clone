package com.gadashov.instagram.service;

import com.gadashov.instagram.model.entity.ConfirmationToken;
import com.gadashov.instagram.model.enums.ConfirmationType;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:42 PM
 */

public interface ConfirmationTokenService {

    ConfirmationToken findByCode(String otp, ConfirmationType type);
}
