package com.backend.social.socialbackendapis.service.impl;

import com.backend.social.socialbackendapis.entity.Category;
import com.backend.social.socialbackendapis.exception.ResourceNotFoundException;
import com.backend.social.socialbackendapis.payload.CategoryDto;
import com.backend.social.socialbackendapis.payload.CategoryPaginationResponse;
import com.backend.social.socialbackendapis.repository.CategoryRepository;
import com.backend.social.socialbackendapis.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.dtoToCategory(categoryDto);
        Category createdCategory = this.categoryRepository.save(category);
        return this.categoryToDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category retrievedCategory = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        retrievedCategory.setTitle(categoryDto.getTitle());
        retrievedCategory.setDescription(categoryDto.getDescription());
        Category updatedCategory = this.categoryRepository.save(retrievedCategory);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        this.categoryRepository.deleteById(categoryId);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category retrievedCategory = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        return this.categoryToDto(retrievedCategory);
    }

    @Override
    public CategoryPaginationResponse getAllCategory(Integer pageNumber, Integer pageSize) {
        Pageable pageInfo = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = this.categoryRepository.findAll(pageInfo);
        List<Category> getAllCategories = categoryPage.getContent();
        List<CategoryDto> allCategories = getAllCategories.stream()
                .map(this::categoryToDto).toList();

        CategoryPaginationResponse categoryPaginationResponse = new CategoryPaginationResponse();
        categoryPaginationResponse.setContent(allCategories);
        categoryPaginationResponse.setPageNumber(categoryPage.getNumber());
        categoryPaginationResponse.setPageSize(categoryPage.getSize());
        categoryPaginationResponse.setTotalElements(categoryPage.getTotalElements());
        categoryPaginationResponse.setTotalPages(categoryPage.getTotalPages());
        categoryPaginationResponse.setLastPage(categoryPage.isLast());
        return categoryPaginationResponse;
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto categoryToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }
}
