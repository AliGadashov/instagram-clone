package com.gadashov.instagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 5:33 PM
 */

@Configuration
public class BeanConfiguration {
    @Bean
    public JavaMailSender javaMailSender(){
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true" );
        properties.setProperty("mail.smtp.starttls.enable", "true");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("gadashovali13@gmail.com");
        mailSender.setPassword("xidg camh mpbs kjtw");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
