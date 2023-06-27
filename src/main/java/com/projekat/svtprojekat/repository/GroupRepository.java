package com.projekat.svtprojekat.repository;

import com.projekat.svtprojekat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findFirstByName(String name);

    Group findGroupById(Long Id);
}
