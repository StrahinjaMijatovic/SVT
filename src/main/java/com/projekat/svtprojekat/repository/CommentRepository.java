package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
