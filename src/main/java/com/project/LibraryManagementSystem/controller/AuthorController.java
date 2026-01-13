package com.project.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.LibraryManagementSystem.dto.author.*;
import com.project.LibraryManagementSystem.service.AuthorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<AuthorResponse> addAuthor(@Valid @RequestBody AuthorRequest request) {
        AuthorResponse response = authorService.addAuthor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponse> getAuthorByID(@PathVariable Long id) {
        AuthorResponse response = authorService.getAuthorById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@Valid @RequestBody AuthorRequest request,
            @PathVariable Long id) {
        AuthorResponse response = authorService.updateAuthorDetails(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AuthorResponse>> getAuthorsList() {
        List<AuthorResponse> responseList = authorService.listAllAuthor();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
