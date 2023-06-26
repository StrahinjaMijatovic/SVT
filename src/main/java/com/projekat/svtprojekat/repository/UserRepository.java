package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);
}
