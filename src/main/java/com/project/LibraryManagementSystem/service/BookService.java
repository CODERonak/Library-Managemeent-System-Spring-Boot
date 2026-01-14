package com.project.LibraryManagementSystem.service;

import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.dto.book.*;
import com.project.LibraryManagementSystem.exceptions.custom.NotFoundException;
import com.project.LibraryManagementSystem.mappers.BookMapper;
import com.project.LibraryManagementSystem.model.entity.*;
import com.project.LibraryManagementSystem.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookResponse addBook(BookRequest request) {

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author id " + request.getAuthorId() + " not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category id " + request.getCategoryId() + " not found"));

        Book book = bookMapper.toModel(request);

        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(null);

        book = bookRepository.save(book);
        return bookMapper.toResponse(book);
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id " + id + " not found"));
        return bookMapper.toResponse(book);
    }

    public BookResponse updateBook(BookRequest request, Long id) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author id " + request.getAuthorId() + " not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category id " + request.getCategoryId() + " not found"));

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id " + id + " not found"));

        bookMapper.updateBookFromRequest(request, existingBook);

        existingBook.setAuthor(author);
        existingBook.setCategory(category);
        existingBook.setAvailableCopies(null);

        Book updatedBook = bookRepository.save(existingBook);

        return bookMapper.toResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id))
            throw new NotFoundException("Book id " + id + " not found");

        bookRepository.deleteById(id);
    }
}
