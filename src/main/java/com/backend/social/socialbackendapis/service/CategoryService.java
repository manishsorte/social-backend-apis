package com.backend.social.socialbackendapis.service;


import com.backend.social.socialbackendapis.payload.CategoryDto;
import com.backend.social.socialbackendapis.payload.CategoryPaginationResponse;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDto getCategory(Integer categoryId);

    CategoryPaginationResponse getAllCategory(Integer pageNumber, Integer pageSize);
}
