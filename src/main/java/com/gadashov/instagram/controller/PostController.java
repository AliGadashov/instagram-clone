package com.gadashov.instagram.controller;


import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.request.CreatePostRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.dto.response.PostResponse;
import com.gadashov.instagram.model.dto.response.Response;
import com.gadashov.instagram.model.entity.User;
import com.gadashov.instagram.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 1:43 PM
 */

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/sharing")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createPost(@AuthenticationPrincipal User user,
                               @NotNull @Valid @RequestPart("data") CreatePostRequest request,
                               @NotNull @RequestPart("media") MultipartFile[] media) {

        log.info("post/controller create post userId: {}, request: {}", user.getId(), request);

        postService.createPost(user, request, media);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("message.post.create_post")
                .build();

        log.info("success post/controller create post userId: {}, response: {}", user.getId(), response);

        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getUserPosts(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "0") Integer page) {
        log.info("post/controller find all post userId: {}, page: {}", user.getId(), page);
        List<PostResponse> postResponseList = postService.getUserPosts(user, page);

        Response response = Response.builder()
                .status(HttpStatus.OK)
                .data(postResponseList)
                .build();

        log.info("success post/controller findAll post userId: {}, page: {}, response: {}", user.getId(), page, response);
        return response;
    }

    @GetMapping("/feed")
    public Response getUserFeed(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "0") Integer page){
        List<PostResponse> postResponse = postService.feed(user, page);
        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .data(postResponse)
                .build();
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getPostById(@AuthenticationPrincipal User user, @PathVariable Long id) {

        log.info("post/controller getPostById  userId: {}, postId: {}", user.getId(), id);

        PostResponse postResponse = postService.getPostById(user, id);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .data(postResponse)
                .build();

        log.info("success post/controller get post by id, userId: {}, postId: {}, response: {}", user.getId(), id, response);

        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Response deletePost(@AuthenticationPrincipal User user, @PathVariable Long id) {

        log.info("post/controller delete post by id  userId: {}, postId: {}", user.getId(), id);

        postService.deletePost(user, id);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("message.post.delete_post")
                .build();

        log.info("success post/controller delete post by id, userId: {}, postId: {}, response: {}", user.getId(), id, response);
        return response;
    }


    //Like
    @GetMapping("/{id}/likes")
    public Response getPostLikes(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestParam(required = false, defaultValue = "0") Integer page) {
        log.info("post/controller getPostLikes userId: {}, postId: {}", user.getId(), id);

        List<LikeResponse> likeResponse = postService.getPostLikes(user, id, page);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .data(likeResponse)
                .build();

        log.info("success post/controller getPostLikes  userId: {}, postId: {}, response: {}", user.getId(), id, response);
        return response;
    }

    @PostMapping("/{id}/like")
    public Response likePost(@AuthenticationPrincipal User user, @PathVariable Long id) {

        log.info("post/controller like post userId: {}, postId: {}", user.getId(), id);

        postService.like(user, id);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("message.post.like_post")
                .build();

        log.info("success post/controller like post  userId: {}, postId: {}, response: {}", user.getId(), id, response);
        return response;
    }


    @PostMapping("/{id}/unlike")
    public Response unlikePost(@AuthenticationPrincipal User user, @PathVariable Long id) {

        log.info("post/controller unlike post userId: {}, postId: {}", user.getId(), id);

        postService.unlike(user, id);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("message.post.un_like_post")
                .build();

        log.info("success post/controller unlike post  userId: {}, postId: {}, response: {}", user.getId(), id, response);
        return response;
    }


    //comments
    @GetMapping("/{id}/comment")
    public Response getPostComments(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestParam(required = false, defaultValue = "0") Integer page) {
        log.info("post/controller getPostComments userId: {}, postId: {}", user.getId(), id);

        List<CommentResponse> likeResponse = postService.getPostComments(user, id, page);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .data(likeResponse)
                .build();

        log.info("success post/controller getPostComments  userId: {}, postId: {}, response: {}", user.getId(), id, response);
        return response;    }

    @PostMapping("/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createComment(@AuthenticationPrincipal User user, @PathVariable Long id, @Valid @RequestBody CreateCommentRequest request) {

        log.info("post/controller create comment userId: {}, postId: {}, request: {}", user.getId(), id, request);

        postService.createComment(user, id, request);

        Response response = Response.builder()
                .status(HttpStatus.CREATED)
                .message("message.post.create_comment")
                .build();

        log.info("success post/controller create comment userId: {}, postId: {}, response: {}", user.getId(), id, response);

        return response;
    }

    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteComment(@AuthenticationPrincipal User user, @PathVariable Long commentId) {

        log.info("post/controller create comment userId: {}, commentId: {}", user.getId(), commentId);

        postService.deleteComment(user, commentId);

        Response response = Response.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("message.post.delete_comment")
                .build();

        log.info("success post/controller create comment userId: {}, postId: {}, response: {}", user.getId(), commentId, response);

        return response;
    }
}
