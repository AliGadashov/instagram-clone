package com.gadashov.instagram.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:44 PM
 */

@Data
public class UserLoginRequest {
    @NotBlank @Size(min = 4) String username;
    @NotBlank @Size(min = 8) String password;

}
