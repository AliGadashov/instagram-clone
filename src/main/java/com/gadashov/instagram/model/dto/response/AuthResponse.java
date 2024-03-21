package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:12 PM
 */

@Value
public class AuthResponse {
    @JsonProperty("access_token")
    String accessToken;
}
