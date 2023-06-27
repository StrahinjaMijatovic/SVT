package com.projekat.svtprojekat.services;

import com.projekat.svtprojekat.dto.UserDTO;
import com.projekat.svtprojekat.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);
    User findUserByUsername(String username);
    User createUser(UserDTO userDTO);
    List<User> findAll();
    void saveUser(User user);
}
