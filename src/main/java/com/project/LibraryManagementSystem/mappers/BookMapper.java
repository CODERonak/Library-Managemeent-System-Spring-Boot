package com.project.LibraryManagementSystem.mappers;

import com.project.LibraryManagementSystem.dto.book.BookRequest;
import com.project.LibraryManagementSystem.dto.book.BookResponse;
import com.project.LibraryManagementSystem.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "availableCopies", ignore = true)
    @Mapping(target = "isbn", ignore = true)
    void updateBookFromRequest(BookRequest bookRequest, @MappingTarget Book book);
}
