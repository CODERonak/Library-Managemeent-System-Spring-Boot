package com.project.LibraryManagementSystem.repository;

import com.project.LibraryManagementSystem.model.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findAllByBorrowerId(Long borrowerId);
}
