package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.model.entity.Follow;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.FollowRepository;
import com.gadashov.instagram.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/17/2024
 * Time: 6:00 PM
 */
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
    private final FollowRepository repository;

    @Override
    public void follow(User follower, User following) {

        if (repository.findByFollowingAndFollower(follower, following)
                .isEmpty() && !follower.getId().equals(following.getId())) {
            Follow build = Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build();
            repository.save(build);

        } else {
            throw new GlobalException(Exceptions.FOLLOWING_ALREADY_EXISTS);
        }
    }

    @Override
    public void unfollow(User user, User unfollow) {
        if (user.getId().equals(unfollow.getId())) {
            throw new GlobalException(Exceptions.FOLLOWING_NOT_FOUND);
        }
        Follow following = repository.findByFollowingAndFollower(user, unfollow)
                .orElseThrow(() -> new GlobalException(Exceptions.FOLLOWING_NOT_FOUND));
        repository.delete(following);
    }


}
