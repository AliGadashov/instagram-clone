package com.gadashov.instagram.service.impl;

import com.gadashov.instagram.mapper.CommentMapper;
import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.entity.Comment;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.CommentRepository;
import com.gadashov.instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:15 PM
 */

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public void createComment(User user, Post post, CreateCommentRequest request) {
        Comment comment = mapper.toEntity(request);
        comment.setUser(user);
        comment.setPost(post);
        repository.save(comment);
    }

    @Override
    public void deleteComment(User user, Long commentId) {
        Comment comment = findById(commentId);
        if(commentIsMine(comment, user)){
            repository.delete(comment);
        }
    }

    @Override
    public List<CommentResponse> getPostComments(Post post, Pageable page) {
        List<Comment> comments = repository.findByPostAndParentIsNull(post, page);
        List<CommentResponse> response = mapper.toResponse(comments);
        return response;
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GlobalException(Exceptions.COMMENT_NOT_FOUND));
    }

    private boolean commentIsMine(Comment comment, User user) {
        if(!comment.getUser().equals(user)){
            throw new GlobalException(Exceptions.COMMENT_NOT_FOUND);
        }
        return true;
    }
}

