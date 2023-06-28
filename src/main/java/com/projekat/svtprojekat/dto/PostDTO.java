package com.projekat.svtprojekat.dto;

import com.projekat.svtprojekat.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Long id;
    @NotBlank
    private String content;

    public PostDTO(Post post){
        this.id = post.getId();
        this.content = post.getContent();
    }

}
