package com.gadashov.instagram.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:44 PM
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow  extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
