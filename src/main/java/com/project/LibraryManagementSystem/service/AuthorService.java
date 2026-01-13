package com.project.LibraryManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.dto.author.*;
import com.project.LibraryManagementSystem.exceptions.custom.NotFoundException;
import com.project.LibraryManagementSystem.mappers.AuthorMapper;
import com.project.LibraryManagementSystem.model.entity.Author;
import com.project.LibraryManagementSystem.repository.AuthorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponse addAuthor(AuthorRequest request) {
        Author author = authorMapper.toModel(request);
        author = authorRepository.save(author);
        return authorMapper.toResponse(author);
    }

    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author id " + id + " not found"));
        return authorMapper.toResponse(author);
    }

    public AuthorResponse updateAuthorDetails(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author id " + id + " not found"));

        author.setName(request.getName());
        author.setBio(request.getBio());

        author = authorRepository.save(author);
        return authorMapper.toResponse(author);
    }

    public List<AuthorResponse> listAllAuthor() {

        List<Author> authors = authorRepository.findAll();
        return authorMapper.toResponseList(authors);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NotFoundException("Author id " + id + " not found");
        }
        authorRepository.deleteById(id);
    }
}
