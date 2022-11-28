package com.backend.social.socialbackendapis.service.impl;

import com.backend.social.socialbackendapis.entity.Category;
import com.backend.social.socialbackendapis.exception.ResourceNotFoundException;
import com.backend.social.socialbackendapis.payload.CategoryDto;
import com.backend.social.socialbackendapis.repository.CategoryRepository;
import com.backend.social.socialbackendapis.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CategoryDto> getAllCategory() {
        List<Category> getAllCategories = this.categoryRepository.findAll();
        List<CategoryDto> allCategories = getAllCategories.stream()
                .map(this::categoryToDto).toList();
        return allCategories;
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto categoryToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }
}
