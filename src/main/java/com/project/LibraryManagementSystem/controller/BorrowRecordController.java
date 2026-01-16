package com.project.LibraryManagementSystem.controller;

import com.project.LibraryManagementSystem.dto.borrowrecord.BorrowRecordRequest;
import com.project.LibraryManagementSystem.dto.borrowrecord.BorrowRecordResponse;
import com.project.LibraryManagementSystem.service.BorrowRecordService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
@AllArgsConstructor
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @PostMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<BorrowRecordResponse> borrowBook(@RequestBody BorrowRecordRequest request) {
        BorrowRecordResponse response = borrowRecordService.borrowBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/return/{borrowerId}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<BorrowRecordResponse> returnBook(@PathVariable Long borrowerId) {
        BorrowRecordResponse response = borrowRecordService.returnBook(borrowerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/history/{borrowerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BorrowRecordResponse>> getBorrowingHistory(@PathVariable Long borrowerId) {
        List<BorrowRecordResponse> response = borrowRecordService.getMyBorrowingHistory(borrowerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
