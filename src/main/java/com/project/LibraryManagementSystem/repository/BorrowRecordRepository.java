package com.project.LibraryManagementSystem.repository;

import com.project.LibraryManagementSystem.model.entity.Book;
import com.project.LibraryManagementSystem.model.entity.BorrowRecord;
import com.project.LibraryManagementSystem.model.entity.User;
import com.project.LibraryManagementSystem.model.enums.Status;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByBorrower(User borrower);

    Optional<BorrowRecord> findByBookAndStatus(Book book, Status status);

    Optional<BorrowRecord> findByIdAndBorrower(Long id, User borrower);

}
