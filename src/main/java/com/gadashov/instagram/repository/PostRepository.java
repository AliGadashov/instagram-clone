package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.model.enums.PrivacyType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 12:41 PM
 */

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN User u ON p.user.id = u.id LEFT JOIN Follow f ON p.user = f.following WHERE (f.follower = :user OR u.privacy = :privacy)")
    List<Post> getUserFeed(User user, PrivacyType privacy, Pageable pageable);

}
