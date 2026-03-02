package com.dauphine.blogger.controllers;

import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    //@GetMapping
  //  public List<Post> retrieveAllPosts() {
      //  return service.getAll();
    //}

    @GetMapping("/{id}")
    public ResponseEntity<Post> retrievePostById(@PathVariable UUID id) {
        Post post = service.getById(id);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Post>> retrievePostsByCategoryId(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(service.getAllByCategoryId(categoryId));
    }

    /*@PostMapping
    public Post createPost(@RequestBody String title, @RequestParam String content, @RequestParam UUID categoryId) {
        return service.create(title, content, categoryId);
    }*/

    /* Ancienne version
    @PostMapping
    public Post createPost(@RequestBody Post request) {
        return service.create(request.getTitle(),request.getContent(),request.getCategory().getId()
        );
    }*/

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post request) {

        Post post = service.create(request.getTitle(),request.getContent(),request.getCategory().getId()
        );

        return ResponseEntity.created(URI.create("/v1/posts/" + post.getId())).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody Post request) {
        return ResponseEntity.ok(service.update(id, request.getTitle(), request.getContent()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {

        List<Post> posts = value == null || value.isBlank()
                ? service.getAll()
                : service.getAllLikeValue(value);
        return ResponseEntity.ok(posts);
    }
}