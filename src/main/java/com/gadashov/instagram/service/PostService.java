package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.request.CreatePostRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.dto.response.PostResponse;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

;import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 1:44 PM
 */

public interface PostService {
    void createPost(User user, CreatePostRequest request, MultipartFile[] media);

    List<PostResponse> getUserPosts(User user, Integer page);

    List<PostResponse> feed(User user, Integer page);

    PostResponse getPostById(User user, Long id);

    void deletePost(User user, Long id);

    List<LikeResponse> getPostLikes(User user, Long id, Integer page);

    Post findPostById(Long postId);

    void like(User user, Long id);

    void unlike(User user, Long id);

    List<CommentResponse> getPostComments(User user, Long id, Integer page);

    void createComment(User user, Long id, CreateCommentRequest request);

    void deleteComment(User user, Long commentId);
}
