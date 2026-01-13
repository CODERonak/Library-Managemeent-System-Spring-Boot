package com.project.LibraryManagementSystem.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Bio cannot be blank")
    private String bio;
}
