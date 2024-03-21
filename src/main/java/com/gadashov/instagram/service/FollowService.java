package com.gadashov.instagram.service;

import com.gadashov.instagram.model.entity.User;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/17/2024
 * Time: 6:00 PM
 */

public interface FollowService {

    void follow(User user, User follow);

    void unfollow(User user, User unfollow);
}
