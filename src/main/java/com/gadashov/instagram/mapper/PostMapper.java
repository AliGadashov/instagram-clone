package com.gadashov.instagram.mapper;

import com.gadashov.instagram.model.dto.request.CreatePostRequest;
import com.gadashov.instagram.model.dto.response.PostResponse;
import com.gadashov.instagram.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 12:55 PM
 */

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {
    Post toEntity(CreatePostRequest request);
    PostResponse toResponse(Post post);
    List<PostResponse> toResponse(List<Post> posts);

}
