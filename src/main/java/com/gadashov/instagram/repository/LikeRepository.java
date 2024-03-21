package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Like;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:30 PM
 */

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);

    List<Like> findByPost(Post post, Pageable pageable);
}