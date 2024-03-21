package com.gadashov.instagram.service;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:18 PM
 */

public interface MailService {
    void sendMessage(String email, String subject, String key);
}
