package com.gadashov.instagram.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:37 PM
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer otp;

    LocalDateTime expiredTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    User user;
}
