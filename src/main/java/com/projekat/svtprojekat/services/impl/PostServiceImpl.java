package com.projekat.svtprojekat.services.impl;

import com.projekat.svtprojekat.dto.PostDTO;
import com.projekat.svtprojekat.entity.Post;
import com.projekat.svtprojekat.repository.PostRepository;
import com.projekat.svtprojekat.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post findPostByContent(String content) {
        Optional<Post> post = postRepository.findPostByContent(content);
        if(!post.isEmpty()){
            return post.get();
        }
        return null;
    }

    @Override
    public Post createPost(PostDTO postDTO) {

        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setCreationDate(LocalDateTime.now());
        post = postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }
}
