package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gadashov.instagram.model.entity.User;
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
public class FollowResponse {
    Long id;
    User user;
    @JsonProperty("creation_time")
    LocalDateTime createdAt;
}
