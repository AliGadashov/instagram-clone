package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Comment;
import com.gadashov.instagram.model.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:28 PM
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostAndParentIsNull(Post post, Pageable page);
}