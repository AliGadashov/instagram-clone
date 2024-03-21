package com.gadashov.instagram.mapper;

import com.gadashov.instagram.model.dto.response.MediaResponse;
import com.gadashov.instagram.model.entity.Media;
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
public interface MediaMapper {
    List<MediaResponse> toResponse(List<Media> media);
    MediaResponse toResponse(Media media);
}
