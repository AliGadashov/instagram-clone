package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:19 PM
 */

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    @Override
    public void sendMessage(String email, String subject, String key) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setFrom("gadashovali13@gmail.com");
            helper.setSubject(subject);
            helper.setText(key, true);


            mailSender.send(message);
        } catch (MessagingException e) {
            throw new GlobalException(Exceptions.EMAIL_SEND_ERROR);
        }

    }
}
