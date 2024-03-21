package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:21 PM
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    Long id;

    String name;

    String username;

    String phoneNumber;

    String email;

    LocalDate birthdate;

    Integer postCount;

    Integer followerCount;

    Integer followingCount;

    @JsonProperty("joined_time")
    LocalDateTime createdAt;
}
