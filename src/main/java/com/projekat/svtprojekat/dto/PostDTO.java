package com.projekat.svtprojekat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostDTO {

    private Long id;

    @NotBlank
    private String content;
    @NotNull
    private String user;

    @NotBlank
    private String creationDate;

    public PostDTO() {
    }

}
