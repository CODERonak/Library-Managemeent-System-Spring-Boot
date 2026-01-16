package com.project.LibraryManagementSystem.dto.borrowrecord;

import lombok.Data;

@Data
public class BorrowRecordRequest {
    private Long userId;
    private Long bookId;
}
