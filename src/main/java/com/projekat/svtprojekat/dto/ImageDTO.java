package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Image;

import javax.validation.constraints.NotNull;

public class ImageDTO {

    @NotNull
    private String path;

    public ImageDTO (Image image) {

        this.path = image.getPath();
    }
}
