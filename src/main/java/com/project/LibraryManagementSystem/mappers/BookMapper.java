package com.project.LibraryManagementSystem.mappers;

import com.project.LibraryManagementSystem.dto.book.BookRequest;
import com.project.LibraryManagementSystem.dto.book.BookResponse;
import com.project.LibraryManagementSystem.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestToBook(BookRequest bookRequest);

    @Mapping(source = "author", target = "author")
    @Mapping(source = "category", target = "category")
    BookResponse bookToBookResponse(Book book);
}
