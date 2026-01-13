package com.project.LibraryManagementSystem.mappers;

import com.project.LibraryManagementSystem.dto.category.*;
import com.project.LibraryManagementSystem.model.entity.Category;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toModel(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categories);
}
