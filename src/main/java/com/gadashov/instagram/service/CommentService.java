package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.entity.Comment;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:15 PM
 */

public interface CommentService {
    void createComment(User user, Post post, CreateCommentRequest request);

    void deleteComment(User user, Long commentId);

    List<CommentResponse> getPostComments(Post post, Pageable page);

    Comment findById(Long id);
}
