package com.projekat.svtprojekat.controller;

import com.projekat.svtprojekat.dto.GroupDTO;
import com.projekat.svtprojekat.dto.GroupReqDto;
import com.projekat.svtprojekat.entity.Group;
import com.projekat.svtprojekat.entity.User;
import com.projekat.svtprojekat.services.GroupService;
import com.projekat.svtprojekat.services.PostService;
import com.projekat.svtprojekat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/groups")
public class GroupController {

    @Autowired
    UserService userService;

    GroupService groupService;

    PostService postService;

    @Autowired
    public GroupController(GroupService groupService, PostService postService) {
        this.groupService = groupService;
        this.postService = postService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO newGroup) {

        Group createdGroup = groupService.createGroup(newGroup);

        if(createdGroup == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        GroupDTO groupDTO = new GroupDTO(createdGroup);

        return new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GroupDTO> edit(@RequestBody @Validated GroupDTO editGroup){
        Group edit = groupService.findGroupById(editGroup.getId());
        edit.setDescription(editGroup.getDescription());
        edit.setName(editGroup.getName());
        groupService.saveGroup(edit);

        GroupDTO groupDTO = new GroupDTO(edit);
        return  new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public HttpStatus deleteGroup(Principal user, @RequestBody @Validated Long id) {
        User groupAdmin = this.userService.findUserByUsername(user.getName());

        if( this.groupService.findGroupById(id).getGroupAdmin() == groupAdmin.getId())
        {
            this.groupService.deleteGroup(id);
            return HttpStatus.ACCEPTED;
        }
        else return HttpStatus.NOT_ACCEPTABLE;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Group saveGroup(Principal user, @RequestBody @Validated GroupReqDto dto) {
        User groupAdmin = this.userService.findUserByUsername(user.getName());

        if( this.groupService.findGroupById(dto.getId()).getGroupAdmin() == groupAdmin.getId())
        {
            Group newGroup =  groupService.findGroupById(dto.getId());
            newGroup.setName(dto.getName());
            newGroup.setDescription(dto.getDescription());
            groupService.saveGroup(newGroup);

        }
        else return null;
        return null;
    }

    @GetMapping("/all")
    public List<Group> loadAll(){return this.groupService.findAll();}
}
