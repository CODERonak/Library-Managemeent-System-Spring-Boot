package com.project.LibraryManagementSystem.repository;

import com.project.LibraryManagementSystem.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    Page<Book> findByAuthorId(Long authorId, Pageable pageable);

    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
}
