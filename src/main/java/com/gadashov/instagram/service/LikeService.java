package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:05 PM
 */

public interface LikeService {
    void like(User user, Post post);
    void unlike(User user, Post post);

    List<LikeResponse> getPostLikes(Post post, Pageable pageable);
}
