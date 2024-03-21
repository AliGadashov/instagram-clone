package com.gadashov.instagram.model.entity;

import com.gadashov.instagram.model.enums.ConfirmationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/12/2024
 * Time: 3:45 PM
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfirmationToken extends BaseModel {

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime expireTime;

    @Enumerated(EnumType.STRING)
    private ConfirmationType type;
}
