package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findReactionsByPostId(Long id);
    List<Reaction> findReactionsByUserId(Long id);
}
