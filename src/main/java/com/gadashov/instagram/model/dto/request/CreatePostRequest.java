package com.gadashov.instagram.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:43 PM
 */

@Data
public class CreatePostRequest {
    @NotBlank String message;
}
