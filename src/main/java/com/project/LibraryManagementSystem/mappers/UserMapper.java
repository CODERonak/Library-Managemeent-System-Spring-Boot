package com.project.LibraryManagementSystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.LibraryManagementSystem.dto.auth.*;
import com.project.LibraryManagementSystem.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toModel(UserRequest request);

    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResponse toResponse(User model);
}
