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

        Book updateBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id " + id + " not found"));

        updateBook.setTitle(request.getTitle());
        updateBook.setIsbn(request.getIsbn());
        updateBook.setDescription(request.getDescription());
        updateBook.setTotalCopies(request.getTotalCopies());
        updateBook.setAuthor(author);
        updateBook.setCategory(category);
        updateBook.setAvailableCopies(null);
        updateBook = bookRepository.save(updateBook);
        return bookMapper.toResponse(updateBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id))
            throw new NotFoundException("Book id " + id + " not found");

        bookRepository.deleteById(id);
    }
}
