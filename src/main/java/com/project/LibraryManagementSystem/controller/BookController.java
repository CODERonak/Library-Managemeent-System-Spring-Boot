package com.project.LibraryManagementSystem.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.LibraryManagementSystem.dto.book.BookRequest;
import com.project.LibraryManagementSystem.dto.book.BookResponse;
import com.project.LibraryManagementSystem.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookRequest request, @PathVariable Long id) {
        BookResponse response = bookService.updateBook(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BookResponse>> getPaginatedBooks(Pageable pageable) {
        Page<BookResponse> books = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<BookResponse> getBookByTitle(@PathVariable String title) {
        BookResponse response = bookService.getBookByTitle(title);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
