package com.project.LibraryManagementSystem.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.dto.borrowrecord.*;
import com.project.LibraryManagementSystem.exceptions.custom.NotFoundException;
import com.project.LibraryManagementSystem.mappers.BorrowRecordMapper;
import com.project.LibraryManagementSystem.model.entity.Book;
import com.project.LibraryManagementSystem.model.entity.BorrowRecord;
import com.project.LibraryManagementSystem.model.entity.User;
import com.project.LibraryManagementSystem.model.enums.Status;
import com.project.LibraryManagementSystem.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    public BorrowRecordResponse borrowBook(BorrowRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User id " + request.getUserId() + " not found"));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book id " + request.getBookId() + " not found"));

        BorrowRecord borrow = new BorrowRecord();
        borrow.setBorrower(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDateTime.now());
        borrow.setReturnDate(LocalDateTime.now().plusWeeks(1));
        borrow.setStatus(Status.BORROWED);

        borrow = borrowRecordRepository.save(borrow);
        return borrowRecordMapper.toResponse(borrow);
    }

    public BorrowRecordResponse returnBook(Long borrowId) {

        BorrowRecord borrow = borrowRecordRepository.findById(borrowId)
                .orElseThrow(() -> new NotFoundException("Borrow id " + borrowId + " not found"));

        borrow.setReturnDate(LocalDateTime.now());
        borrow.setStatus(Status.RETURNED);
        borrow = borrowRecordRepository.save(borrow);
        return borrowRecordMapper.toResponse(borrow);
    }
}
