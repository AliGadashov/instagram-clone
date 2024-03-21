package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.mapper.LikeMapper;
import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.entity.Like;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.LikeRepository;
import com.gadashov.instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:07 PM
 */

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final LikeMapper mapper;

    @Override
    public void like(User user, Post post) {
        if(repository.findByUserAndPost(user, post).isEmpty()){
            Like like = Like.builder().post(post).user(user).build();
            repository.save(like);
        }else {
            throw new GlobalException(Exceptions.LIKE_ALREADY_EXISTS);
        }
    }

    @Override
    public void unlike(User user, Post post) {
        Like like = repository.findByUserAndPost(user, post)
                .orElseThrow(() -> new GlobalException(Exceptions.LIKE_NOT_FOUND));
        repository.delete(like);
    }

    @Override
    public List<LikeResponse> getPostLikes(Post post, Pageable pageable) {
        List<Like> likes = repository.findByPost(post, pageable);
        return likes.stream().map(mapper::toResponse).toList();
    }
}
