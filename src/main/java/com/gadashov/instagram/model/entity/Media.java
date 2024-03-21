package com.gadashov.instagram.model.entity;

import com.gadashov.instagram.model.enums.PostType;
import jakarta.persistence.*;
import lombok.*;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:52 PM
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Media extends BaseModel {

    private PostType type;

    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", type=" + this.getType() +
                ", url='" + this.getUrl() + '\'' +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
