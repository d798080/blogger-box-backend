package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.findAll()
                .stream()
                .filter(post -> post.getCategory() != null
                        && categoryId.equals(post.getCategory().getId()))
                .toList();
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = new Category();
        category.setId(categoryId);

        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);

        if (post == null) {
            return null;
        }

        post.setTitle(title);
        post.setContent(content);
        return repository.save(post);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> getAllLikeValue(String value) {
        return repository.findAllByTitleOrContent(value);
    }
}