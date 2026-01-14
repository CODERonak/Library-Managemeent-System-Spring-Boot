package com.project.LibraryManagementSystem.dto.book;

import com.project.LibraryManagementSystem.dto.author.AuthorResponse;
import com.project.LibraryManagementSystem.dto.category.CategoryResponse;
import lombok.Data;

@Data
public class BookResponse {
    private Long id;

    private String title;

    private String description;

    private String isbn;

    private AuthorResponse author;

    private CategoryResponse category;

    private Integer availableCopies;

    private Integer totalCopies;

}
