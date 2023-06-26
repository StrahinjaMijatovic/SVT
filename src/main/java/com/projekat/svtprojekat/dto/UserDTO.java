package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.entity.enums.Roles;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_]{3,21}$")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    @NotBlank
    private LocalDateTime lastLogin;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    private Roles role;

    public UserDTO(User createdUser) {
        this.id = createdUser.getId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.role = createdUser.getRole();
        this.lastLogin = createdUser.getLastLogin();
        this.firstname = createdUser.getFirstName();
        this.lastname = createdUser.getLastName();
    }
}
