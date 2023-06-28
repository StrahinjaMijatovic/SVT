package com.projekat.svtprojekat.controller;

import com.projekat.svtprojekat.dto.PostDTO;
import com.projekat.svtprojekat.entity.Post;
import com.projekat.svtprojekat.services.PostService;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PostDTO> create(@RequestBody @Validated PostDTO newPost){

        Post createdPost = postService.createPost(newPost);

        if(createdPost == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PostDTO postDTO = new PostDTO(createdPost);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public void delete(@RequestParam Integer id){postService.deletePost(id.longValue());}

    @GetMapping("/all")
    public List<Post> loadAll(){return this.postService.findAll();}

    @PutMapping("/edit")
    public ResponseEntity<PostDTO> edit(@RequestBody @Validated PostDTO editPost){
        Post edit = postService.findPostByContent(editPost.getContent());
        edit.setContent(editPost.getContent());
        postService.savePost(edit);

        PostDTO postDTO = new PostDTO(edit);
        return  new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }
}
