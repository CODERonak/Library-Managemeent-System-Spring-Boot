package com.project.LibraryManagementSystem.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotNull(message = "Author ID cannot be null")
    private Long authorId;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Total copies cannot be null")
    @Min(1)
    private Integer totalCopies;
}
