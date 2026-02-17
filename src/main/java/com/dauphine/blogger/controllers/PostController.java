package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> retrieveAllPosts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Post retrievePostById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Post> retrievePostsByCategoryId(@PathVariable UUID categoryId) {
        return service.getAllByCategoryId(categoryId);
    }

    @PostMapping
    public Post createPost(@RequestBody String title, @RequestParam String content, @RequestParam UUID categoryId) {
        return service.create(title, content, categoryId);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestParam String title, @RequestParam String content) {
        return service.update(id, title, content);
    }

    @DeleteMapping("/{id}")
    public boolean deletePost(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}