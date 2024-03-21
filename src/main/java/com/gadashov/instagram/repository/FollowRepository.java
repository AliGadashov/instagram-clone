package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Follow;
import com.gadashov.instagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/16/2024
 * Time: 2:10 PM
 */

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingAndFollower(User following, User follower);

}