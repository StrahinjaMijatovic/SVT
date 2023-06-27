package com.projekat.svtprojekat.services;

import com.projekat.svtprojekat.entity.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReactionService {

    Reaction createReaction(Reaction reaction);
    List<Reaction> findReactionsByPostId(Long id);
    List<Reaction> findReactionsByUserId(Long id);
    List<Reaction> findAll();
    Optional<Reaction> findOne(Long id);
    Reaction updateReaction(Reaction reaction);
}
