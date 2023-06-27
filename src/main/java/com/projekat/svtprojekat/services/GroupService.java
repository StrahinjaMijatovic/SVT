package com.projekat.svtprojekat.services;

import com.projekat.svtprojekat.dto.GroupDTO;
import com.projekat.svtprojekat.entity.Group;

import java.util.List;

public interface GroupService {

    Group findGroupById(Long id);
    Group findGroupByName(String name);

    Group createGroup(GroupDTO groupDTO);

    List<Group> findAll();

    void saveGroup(Group group);

    void deleteGroup(Long id);

//    void suspendGroup(Long groupId, Long userId, String reason);
}
