package com.project.LibraryManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.LibraryManagementSystem.dto.category.CategoryRequest;
import com.project.LibraryManagementSystem.dto.category.CategoryResponse;
import com.project.LibraryManagementSystem.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.addCategory(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody CategoryRequest request,
            @PathVariable Long id) {
        CategoryResponse response = categoryService.updateCategory(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listCategory() {
        List<CategoryResponse> response = categoryService.listAllCategory();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
