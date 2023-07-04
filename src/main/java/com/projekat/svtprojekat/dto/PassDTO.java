package com.projekat.svtprojekat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassDTO {
    private String oldPassword1;
    private String oldPassword2;
    private String newPassword;

}
