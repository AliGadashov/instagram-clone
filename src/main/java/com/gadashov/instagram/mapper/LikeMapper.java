package com.gadashov.instagram.mapper;

import com.gadashov.instagram.model.dto.response.LikeResponse;
import com.gadashov.instagram.model.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:52 PM
 */


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LikeMapper {
    LikeResponse toResponse(Like like);
    List<LikeResponse> toResponse(List<Like> likes);
}
