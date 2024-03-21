package com.gadashov.instagram.mapper;

import com.gadashov.instagram.model.dto.request.CreateCommentRequest;
import com.gadashov.instagram.model.dto.response.CommentResponse;
import com.gadashov.instagram.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:53 PM
 */



@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {
    Comment toEntity(CreateCommentRequest request);
    CommentResponse toResponse(Comment comment);
    List<CommentResponse> toResponse(List<Comment> comment);
}
