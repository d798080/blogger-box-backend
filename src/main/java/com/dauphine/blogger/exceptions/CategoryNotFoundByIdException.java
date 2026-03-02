package com.dauphine.blogger.exceptions;

public class CategoryNotFoundByIdException extends RuntimeException {
    public CategoryNotFoundByIdException(String id) {
        super("Category not found with id: " + id);
    }
}