package com.projekat.svtprojekat.services;

import com.projekat.svtprojekat.dto.PasswordDTO;
import com.projekat.svtprojekat.dto.UserDTO;
import com.projekat.svtprojekat.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User findUserById(Long id);
    User findUserByUsername(String username);
    User createUser(UserDTO userDTO);
    List<User> findAll();
    void saveUser(User user);

    ResponseEntity<UserDTO> changePassword(PasswordDTO passwordDTO);

    void ChangeUserPassword(String username, String password);
}
