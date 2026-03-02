package com.dauphine.blogger.exceptions;

public class PostNotFoundByIdException extends RuntimeException {

    public PostNotFoundByIdException(String id) {
        super("Post not found with id: " + id);
    }
}