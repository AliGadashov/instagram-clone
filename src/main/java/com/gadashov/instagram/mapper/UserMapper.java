package com.gadashov.instagram.mapper;

import com.gadashov.instagram.model.dto.request.UserRegisterRequest;
import com.gadashov.instagram.model.dto.response.UserResponse;
import com.gadashov.instagram.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 11:00 AM
 */

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toEntity(UserRegisterRequest request);

    UserResponse toResponse(User user);
}
