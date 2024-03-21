package com.gadashov.instagram.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 9:02 PM
 */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Comment extends BaseModel {
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> children;

    @Override
    public String toString() {
        return "Comment{" +
                "id="+ super.getId() +
                ", comment='" + this.getComment() + '\'' +
                ", user=" + this.getUser() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}