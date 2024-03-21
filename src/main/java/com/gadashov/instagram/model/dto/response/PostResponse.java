package com.gadashov.instagram.model.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:40 PM
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    Long id;
    String content;
    @JsonProperty("total_likes")
    Integer totalLikes;
    @JsonProperty("total_comments")
    Integer totalComments;
    List<MediaResponse> media;
    UserResponse user;
    @JsonProperty("creation_time")
    LocalDateTime createdAt;
}