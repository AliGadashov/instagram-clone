package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.mapper.UserMapper;
import com.gadashov.instagram.model.dto.request.UpdateActiveTypeRequest;
import com.gadashov.instagram.model.dto.response.UserResponse;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.UserRepository;
import com.gadashov.instagram.service.FollowService;
import com.gadashov.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 12:56 PM
 */


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final FollowService followService;


    @Override
    public UserResponse getUserById(User user, Long id) {
        User findUser = this.findUserById(id);
        UserResponse response = mapper.toResponse(findUser);
        return response;
    }

    @Override
    public void updateActiveType(User user, UpdateActiveTypeRequest request) {
        user.setPrivacy(request.getPrivacy());
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new GlobalException(Exceptions.USER_NOT_FOUND));
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GlobalException(Exceptions.USER_NOT_FOUND));
    }

    @Override
    public void follow(User user, Long id) {
        User follow = this.findUserById(id);
        followService.follow(user, follow);
    }

    @Override
    public void unfollow(User user, Long id) {
        User unfollow = this.findUserById(id);
        followService.unfollow(user, unfollow);
    }

    @Override
    public List<UserResponse> followings(User user, Integer page) {
        return null;
    }

    @Override
    public List<UserResponse> followers(User user, Integer page) {
        return null;
    }
}
