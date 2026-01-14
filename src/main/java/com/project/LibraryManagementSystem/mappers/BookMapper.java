package com.project.LibraryManagementSystem.mappers;

import com.project.LibraryManagementSystem.dto.book.BookRequest;
import com.project.LibraryManagementSystem.dto.book.BookResponse;
import com.project.LibraryManagementSystem.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "availableCopies", ignore = true)
    Book toModel(BookRequest bookRequest);

    @Mapping(source = "author", target = "author")
    @Mapping(source = "category", target = "category")
    BookResponse toResponse(Book book);
}
