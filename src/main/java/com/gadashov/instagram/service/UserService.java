package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.request.UpdateActiveTypeRequest;
import com.gadashov.instagram.model.dto.response.UserResponse;
import com.gadashov.instagram.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 1:40 PM
 */

public interface UserService extends UserDetailsService{
    UserResponse getUserById(User user, Long id);
    void updateActiveType(User user, UpdateActiveTypeRequest request);

    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;

    User findUserById(Long id);
    void follow(User user, Long id);
    void unfollow(User user, Long id);
    List<UserResponse> followings(User user, Integer page);
    List<UserResponse> followers(User user, Integer page);}
