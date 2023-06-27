package com.projekat.svtprojekat.services.impl;

import com.projekat.svtprojekat.dto.UserDTO;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.entity.enums.Roles;
import com.projekat.svtprojekat.repository.UserRepository;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public User createUser(UserDTO userDTO) {

        Optional<User> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(Roles.USER);
        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstname());
        newUser.setLastName(userDTO.getLastname());
        newUser.setLastLogin(LocalDateTime.now());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

}
