package com.projekat.svtprojekat.services.impl;

import com.projekat.svtprojekat.dto.GroupDTO;
import com.projekat.svtprojekat.entity.Group;
import com.projekat.svtprojekat.repository.GroupRepository;
import com.projekat.svtprojekat.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group findGroupById(Long Id) {
        return this.groupRepository.findGroupById(Id);

    }

    @Override
    public Group findGroupByName(String name) {
        Optional<Group> group = groupRepository.findFirstByName(name);
        return group.orElse(null);
    }

    @Override
    public Group createGroup(GroupDTO groupDTO) {
        Optional<Group> group = groupRepository.findFirstByName(groupDTO.getName());

        if(group.isPresent()){
            return null;
        }
        Group newGroup = new Group();
        newGroup.setName(groupDTO.getName());
        newGroup.setDescription(groupDTO.getDescription());
        newGroup.setCreationDate(LocalDateTime.now());
        newGroup.setSuspended(false);
        newGroup = groupRepository.save(newGroup);

        return  newGroup;
    }

    @Override
    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long Id) {
        Group grupa =  this.groupRepository.findGroupById(Id);
        grupa.setDeleted(true);
        this.groupRepository.save(grupa);
    }

//    @Override
//    public void suspendGroup(Long groupId, Long userId, String reason) {
//        User user = userRepository.findById(userId).orElse(null);
//        if (user !=null){
//            if (user.getRole() == Roles.ADMIN){
//                groupRepository.suspendGroup(reason, groupId);
//            }
//        }
//    }
}
