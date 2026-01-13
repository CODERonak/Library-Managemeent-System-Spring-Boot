package com.project.LibraryManagementSystem.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.LibraryManagementSystem.dto.author.*;
import com.project.LibraryManagementSystem.model.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    Author toModel(AuthorRequest request);

    AuthorResponse toResponse(Author model);

    List<AuthorResponse> toResponseList(List<Author> models);
}
