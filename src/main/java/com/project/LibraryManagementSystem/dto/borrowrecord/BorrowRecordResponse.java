package com.project.LibraryManagementSystem.dto.borrowrecord;

import com.project.LibraryManagementSystem.dto.auth.UserResponse;
import com.project.LibraryManagementSystem.dto.book.BookResponse;
import lombok.Data;



import java.time.LocalDate;

@Data
public class BorrowRecordResponse {
    private Long id;

    private BookResponse book;

    private UserResponse borrower;

    private LocalDate borrowDate;

    private LocalDate returnDate;
    
    private String status;
}
