package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.mapper.PostMapper;
import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.request.CreatePostRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.dto.response.PostResponse;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.enums.PrivacyType;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.PostRepository;
import com.gadashov.instagram.service.CommentService;
import com.gadashov.instagram.service.LikeService;
import com.gadashov.instagram.service.MediaService;
import com.gadashov.instagram.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/16/2024
 * Time: 3:54 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final PostMapper mapper;

    private final CommentService commentService;
    private final MediaService mediaService;
    private final LikeService likeService;
    @Override
    @Transactional
    public void createPost(User user, CreatePostRequest request, MultipartFile[] media) {
        Post post = mapper.toEntity(request);
        post.setUser(user);
        Post save = repository.save(post);
        log.info(save.getId().toString());
        mediaService.save(save, media);
    }

    @Override
    public List<PostResponse> feed(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Post> posts = repository.getUserFeed(user, PrivacyType.PUBLIC, pageable);
        return posts.stream().map(mapper::toResponse).toList();
    }
    @Override
    public PostResponse getPostById(User user, Long id) {
        Post post = findPostById(id);
        PostResponse response = mapper.toResponse(post);
        return response;
    }

    @Override
    public void deletePost(User user, Long id) {
        Post post = findPostById(id);
        if (postIsMine(post, user)){
            repository.delete(post);
        }
    }

    @Override
    public Post findPostById(Long postId) {
        return repository.findById(postId)
                .orElseThrow(() -> new GlobalException(Exceptions.POST_NOT_FOUND));
    }

    @Override
    public void like(User user, Long postId) {
        Post post = findPostById(postId);
        likeService.like(user, post);
    }

    @Override
    public void unlike(User user, Long postId) {
        Post post = findPostById(postId);
        likeService.unlike(user, post);
    }

    @Override
    public void createComment(User user, Long postId, CreateCommentRequest request) {
        Post post = findPostById(postId);
        commentService.createComment(user, post, request);
    }

    @Override
    public void deleteComment(User user, Long commentId) {
        commentService.deleteComment(user, commentId);
    }

    @Override
    public List<LikeResponse> getPostLikes(User user, Long id, Integer page) {
        Post post = findPostById(id);
        Pageable pageable = PageRequest.of(page, 10);
        List<LikeResponse> response = likeService.getPostLikes(post, pageable);
        return response;
    }

    @Override
    public List<CommentResponse> getPostComments(User user, Long id, Integer page) {
        Post post = findPostById(id);
        Pageable pageable = PageRequest.of(page, 10);
        List<CommentResponse> response = commentService.getPostComments(post, pageable);
        return response;
    }

    @Override
    public List<PostResponse> getUserPosts(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Post> posts = repository.findByUser(user,pageable);
        return posts.stream().map(mapper::toResponse).toList();
    }

    public boolean postIsMine(Post post, User user){
        if (!post.getUser().equals(user)){
            throw new GlobalException(Exceptions.NOT_YOUR_POST);
        }
        return true;
    }
}
