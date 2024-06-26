package com.gadashov.instagram.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:31 PM
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseModel {
    private String content;

    @Builder.Default
    private Integer totalLikes = 0;

    @Builder.Default
    private Integer totalComments = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Media> media;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", content=" + this.getContent() +
                ", totalLikes=" + this.getTotalLikes() +
                ", totalComments=" + this.getTotalComments() +
                ", media=" + media +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}