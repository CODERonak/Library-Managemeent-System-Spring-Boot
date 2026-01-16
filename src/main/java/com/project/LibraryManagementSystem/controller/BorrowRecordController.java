package com.project.LibraryManagementSystem.controller;

import com.project.LibraryManagementSystem.dto.borrowrecord.BorrowRecordRequest;
import com.project.LibraryManagementSystem.dto.borrowrecord.BorrowRecordResponse;
import com.project.LibraryManagementSystem.service.BorrowRecordService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
