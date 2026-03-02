package com.dauphine.blogger.exceptions;

public class CategoryNameAlreadyExistsException extends RuntimeException {

    public CategoryNameAlreadyExistsException(String name) {
        super("Category with name '" + name + "' already exists");
    }
}