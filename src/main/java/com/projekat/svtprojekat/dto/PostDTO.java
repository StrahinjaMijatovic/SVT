package com.projekat.svtprojekat.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostDTO {

    private Long id;

    @NotBlank
    private String content;
    @NotNull
    private String user;

    @NotBlank
    private String creationDate;
}
