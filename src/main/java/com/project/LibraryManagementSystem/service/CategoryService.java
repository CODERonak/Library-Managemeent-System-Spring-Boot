package com.project.LibraryManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.dto.category.*;
import com.project.LibraryManagementSystem.exceptions.custom.NotFoundException;
import com.project.LibraryManagementSystem.mappers.CategoryMapper;
import com.project.LibraryManagementSystem.model.entity.Category;
import com.project.LibraryManagementSystem.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse addCategory(CategoryRequest request) {

        if (categoryRepository.existsByName(request.getName()))
            throw new RuntimeException("Category already exists");

        Category category = categoryMapper.toModel(request);
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    public CategoryResponse updateCategory(CategoryRequest request, Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id " + id + " not found"));
        category.setName(request.getName());

        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    public List<CategoryResponse> listAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponseList(categories);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id))
            throw new NotFoundException("Category id " + id + " not found");

        categoryRepository.deleteById(id);
    }

}
