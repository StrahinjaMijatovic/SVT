package com.projekat.svtprojekat.controller;

import com.projekat.svtprojekat.dto.PostUpdateDTO;
import com.projekat.svtprojekat.dto.ReactionDTO;
import com.projekat.svtprojekat.entity.Post;
import com.projekat.svtprojekat.entity.Reaction;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.services.PostService;
import com.projekat.svtprojekat.services.ReactionService;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    ReactionService reactionService;


    @PostMapping("/createPost")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post createPost(Principal user, @RequestBody @Validated PostUpdateDTO dto) {

        User u = this.userService.findUserByUsername(user.getName());

        Post post = this.postService.createPost(dto.getText(),u.getId());
        u.getPosts().add(post);
        userService.saveUser(u);
        return post;
    }

    @DeleteMapping()
    public void delete(@RequestParam Integer id){postService.deletePost(id.longValue());}

    @GetMapping("/all")
    public List<Post> loadAll(){return this.postService.findAll();}

//    @PutMapping("/edit")
//    public ResponseEntity<PostDTO> edit(@RequestBody @Validated PostDTO editPost){
//        Post edit = postService.findPostByContent(editPost.getContent());
//        edit.setContent(editPost.getContent());
//        postService.savePost(edit);
//
//        PostDTO postDTO = new PostDTO(edit);
//        return  new ResponseEntity<>(postDTO, HttpStatus.CREATED);
//    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post getPost(@PathVariable Long postId) {
        Post post = postService.findOne(postId);
        return post;
    }

    @PostMapping("/{id}/vote")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> vote(@PathVariable("id") Long id, @RequestBody @Valid ReactionDTO reactionDTO,
                                  BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid json");
        }
        Optional<Post> post = postService.findPostById(id);
        if (post.isEmpty()) {
            return ResponseEntity.badRequest().body("Post with given id doesn't exist");
        }
        User loggedUser = userService.findUserByUsername(auth.getName());
        List<Reaction> reactions = reactionService.findReactionsByUserId(loggedUser.getId());
        if (!reactions.isEmpty()) {
            for (Reaction reaction : post.get().getReactions()) {
                if (reaction.getType().equals(reactionDTO.getType())) {
                    return ResponseEntity.badRequest().body("Can't react twice on the same post!");
                } else {
                    Optional<Reaction> reaction1 = reactionService.findOne(reaction.getId());
                    if (reaction1.isPresent()) {
                        reaction1.get().setType(reactionDTO.getType());
                        var updatedReaction = reactionService.updateReaction(reaction1.get());
                        return ResponseEntity.ok(updatedReaction.getType());
                    }
                }
            }
        }
        Reaction reaction = new Reaction(reactionDTO.getType(), loggedUser, post.get());
        var createdReaction = reactionService.createReaction(reaction);
        return ResponseEntity.ok(createdReaction.getId());
    }
}
