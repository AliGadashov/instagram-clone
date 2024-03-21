package com.gadashov.instagram.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:58 PM
 */
@Entity
@Table(name = "post_like")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Like extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", user=" + this.getUser() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}