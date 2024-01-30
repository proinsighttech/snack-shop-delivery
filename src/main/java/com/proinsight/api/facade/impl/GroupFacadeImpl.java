package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.GroupInputDisassembler;
import com.proinsight.api.assembler.GroupModelAssembler;
import com.proinsight.api.facade.GroupFacade;
import com.proinsight.api.model.GroupModel;
import com.proinsight.api.model.input.GroupInput;
import com.proinsight.domain.model.Group;
import com.proinsight.domain.repository.GroupRepository;
import com.proinsight.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupFacadeImpl implements GroupFacade {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private GroupInputDisassembler groupInputDisassembler;

    @Override
    public CollectionModel<GroupModel> listAll() {
        List<Group> allGroups = groupRepository.findAll();
        return groupModelAssembler.toCollectionModel(allGroups);
    }

    @Override
    public GroupModel findGroup(Long groupId) {
        Group group = groupService.findOrThrow(groupId);
        return groupModelAssembler.toModel(group);
    }

    @Override
    public GroupModel addGroup(GroupInput groupInput) {
        Group group = groupInputDisassembler.toDomainObject(groupInput);
        group = groupService.save(group);
        return groupModelAssembler.toModel(group);
    }

    @Override
    public GroupModel updateGroup(Long groupId, GroupInput groupInput) {
        Group currentgroup = groupService.findOrThrow(groupId);
        groupInputDisassembler.copyToDomainObject(groupInput, currentgroup);
        currentgroup = groupService.save(currentgroup);
        return groupModelAssembler.toModel(currentgroup);
    }

    @Override
    public void removeGroup(Long groupId) {
        groupService.delete(groupId);
    }
}