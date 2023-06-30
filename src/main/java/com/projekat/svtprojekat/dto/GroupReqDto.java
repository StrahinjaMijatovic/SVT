package com.projekat.svtprojekat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupReqDto {

    private Long id;
    private String name;
    private String description;

    public GroupReqDto() {
    }
}
