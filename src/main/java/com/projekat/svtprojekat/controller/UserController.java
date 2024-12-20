package com.projekat.svtprojekat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekat.svtprojekat.dto.PassDTO;
import com.projekat.svtprojekat.dto.UserDTO;
import com.projekat.svtprojekat.dto.UserLoginDTO;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.security.TokenUtils;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    /* Ili preporucen nacin: Constructor Dependency Injection
    @Autowired
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService, TokenUtils tokenUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }
    */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO newUser){

        User createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserTokenState> createAuthenticationToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
//
//        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
//        // AuthenticationException
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//
//        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
//        // kontekst
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Kreiraj token za tog korisnika
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        String jwt = tokenUtils.generateToken(user);
//        int expiresIn = tokenUtils.getExpiredIn();
//
//        // Vrati token kao odgovor na uspesnu autentifikaciju
//        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @GetMapping("/whoami")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User user(Principal user) {
        return this.userService.findUserByUsername(user.getName());
    }

//    @PostMapping("/changePassword")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<UserDTO> changePassword (@RequestBody @Validated PasswordDTO passwords) {
//        return userService.changePassword(passwords);
//    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus user(Principal user,@RequestBody  String dto ) throws JsonProcessingException {
        User found = this.userService.findUserByUsername(user.getName());
        ObjectMapper mapper = new ObjectMapper();
        PassDTO passValue = mapper.readValue(dto, PassDTO.class);

        if(passwordEncoder.matches(passValue.getOldPassword1(),found.getPassword()))
        {


            this.userService.ChangeUserPassword(user.getName(),passValue.getNewPassword());
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;

    }
}
