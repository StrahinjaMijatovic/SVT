package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostById(Integer id);

    Optional<Post> findPostByContent(String content);
}
