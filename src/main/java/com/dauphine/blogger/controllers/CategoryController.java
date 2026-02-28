package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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



    //@PostMapping
    //public Category createCategory(@RequestBody String name) {
      //  return categoryService.create(name);
    //}

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody String name) {
        Category category = categoryService.create(name);
        return ResponseEntity
                .created(URI.create("/v1/categories/" + category.getId()))
                .body(category);
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
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getALLLikeName(name);
        return ResponseEntity.ok(categories);
    }
}