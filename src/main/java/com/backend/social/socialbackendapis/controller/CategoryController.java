package com.backend.social.socialbackendapis.controller;

import com.backend.social.socialbackendapis.payload.CategoryDto;
import com.backend.social.socialbackendapis.payload.CategoryPaginationResponse;
import com.backend.social.socialbackendapis.service.impl.CategoryServiceImpl;
import com.backend.social.socialbackendapis.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        CategoryDto getCategory = this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(getCategory, HttpStatus.OK);
    }

    @GetMapping("/allCategory")
    public ResponseEntity<CategoryPaginationResponse> getAllCategory(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                                     @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                                     @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                                     @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDirection) {
        CategoryPaginationResponse allCategories = this.categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }
}
