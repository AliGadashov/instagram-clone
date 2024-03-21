package com.gadashov.instagram.service;

import com.gadashov.instagram.model.entity.Otp;
import com.gadashov.instagram.model.entity.User;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:36 PM
 */

public interface ConfirmationOtpService {
    void createdOtpSentToEmail(User user);
    Otp getConfirmationOtp(Integer otp);

}
