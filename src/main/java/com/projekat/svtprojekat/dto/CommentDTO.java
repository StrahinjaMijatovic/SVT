package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CommentDTO {

    private Long id;

    @NotBlank
    private String text;
    @NotNull
    private LocalDate timestamp;

    @NotBlank
    private boolean isDeleted;

    public CommentDTO (Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.timestamp = comment.getTimestamp();
        this.isDeleted = comment.isDeleted();
    }
}
