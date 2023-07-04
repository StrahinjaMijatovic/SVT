package com.projekat.svtprojekat.services.impl;

import com.projekat.svtprojekat.dto.PasswordDTO;
import com.projekat.svtprojekat.dto.UserDTO;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.entity.enums.Roles;
import com.projekat.svtprojekat.repository.UserRepository;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    @Lazy
    private PasswordEncoder encoder;

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
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setLastLogin(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<UserDTO> changePassword(PasswordDTO passwords) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = this.findUserByUsername(a.getName());

        if(encoder.matches(passwords.getCurrent(), user.getPassword()) && passwords.getConfirm().equals(passwords.getPassword())){
            user.setPassword(encoder.encode(passwords.getPassword()));
            this.saveUser(user);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = new UserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @Override
    public void ChangeUserPassword(String username,String password) {
        Optional<User> user = userRepository.findFirstByUsername(username);

        User u=  user.get();
        u.setPassword(passwordEncoder.encode(password));
        userRepository.save(u);
    }

}
