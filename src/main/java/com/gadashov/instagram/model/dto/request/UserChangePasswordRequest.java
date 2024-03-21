package com.gadashov.instagram.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserChangePasswordRequest {
    @NotBlank @Size(min = 8)
    @JsonProperty("current_password")
    String currentPassword;

    @NotBlank @Size(min = 8)
    @JsonProperty("new_password")
    String newPassword;
    @NotBlank @Size(min = 8)
    @JsonProperty("new_password_repeat")
    String newPasswordRepeat;
}
