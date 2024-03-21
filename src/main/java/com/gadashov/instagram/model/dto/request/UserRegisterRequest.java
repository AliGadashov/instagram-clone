package com.gadashov.instagram.model.dto.request;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:45 PM
 */

@Data
public class UserRegisterRequest {
    @NotBlank String name;
    @NotBlank @Size(min = 4) String username;
    @NotBlank @Size(min = 8) String password;
    @NotBlank @Email String email;
    @NotNull @Past LocalDate birthdate;
}
