package com.padillatom.asadoremotobackend.service;

import com.padillatom.asadoremotobackend.model.request.CategoryRequest;
import com.padillatom.asadoremotobackend.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
