package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Group;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class GroupDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private LocalDateTime creationTime;


    public GroupDTO (Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.creationTime = group.getCreationDate();
    }

    public GroupDTO() {
    }
}
