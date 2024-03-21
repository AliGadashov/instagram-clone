package com.gadashov.instagram.model.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:40 PM
 */
@Data
@Builder
public class LikeResponse {
    Long id;
    UserResponse user;
    @JsonProperty("liked_time")
    LocalDateTime createdAt;
}
