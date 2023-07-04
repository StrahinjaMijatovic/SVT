package com.projekat.svtprojekat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PasswordDTO {

    @NotBlank
    private String username;
    @NotEmpty
    private String current;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirm;

    public PasswordDTO() {
    }

    public PasswordDTO(String username, String current, String password, String confirm) {
        this.username = username;
        this.current = current;
        this.password = password;
        this.confirm = confirm;
    }
}
