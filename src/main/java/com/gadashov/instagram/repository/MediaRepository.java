package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Media;
import com.gadashov.instagram.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 12:42 PM
 */


public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByPost(Post post);
}