package com.project.LibraryManagementSystem.dto.borrowrecord;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BorrowRecordRequest {
    @NotBlank(message = "User ID cannot be blank")
    private Long userId;

    @NotBlank(message = "Book ID cannot be blank")
    private Long bookId;
}
