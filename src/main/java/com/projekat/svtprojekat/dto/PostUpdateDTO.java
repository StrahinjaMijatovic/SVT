package com.projekat.svtprojekat.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostUpdateDTO {

    private Long id;

    @NotBlank
    private String content;
}
