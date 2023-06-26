package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
