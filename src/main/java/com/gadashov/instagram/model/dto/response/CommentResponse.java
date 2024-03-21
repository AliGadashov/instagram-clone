package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:39 PM
 */
@Data
@Builder
public class CommentResponse {
    Long id;
    String comment;
    UserResponse user;
    List<CommentResponse> children;
    @JsonProperty("creation_time")
    LocalDateTime createdAt;
    @JsonProperty("updated_time")
    LocalDateTime updatedAt;
}
