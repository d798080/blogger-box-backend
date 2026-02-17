package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService service) {
        this.categoryService = service;
    }

    //@GetMapping
    //public List<Category> retrieveAllCategories() {
       // return service.getAll();
    //}

    @GetMapping("/{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody String name) {
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        return categoryService.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable UUID id) {
        return categoryService.deleteById(id);
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "retrieve all categories or filter like name"
    )
    public List<Category> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getALLLikeName(name);
        return categories;
    }
}