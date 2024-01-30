package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.GroupModelAssembler;
import com.proinsight.api.facade.UserGroupFacade;
import com.proinsight.api.model.GroupModel;
import com.proinsight.api.security.CheckSecurity;
import com.proinsight.domain.model.User;
import com.proinsight.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public class UserGroupFacadeImpl implements UserGroupFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupModelAssembler grupoModelAssembler;

    public CollectionModel<GroupModel> listAllGroup(Long userId) {
        User user = userService.findOrThrow(userId);
        CollectionModel<GroupModel> gruposModel = grupoModelAssembler.toCollectionModel(user.getGroups());
        return gruposModel;
    }

    public ResponseEntity<Void> removeGroup(Long userId, Long groupId) {
        userService.removeGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> addGroup(Long userId, Long groupId) {
        userService.addGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }

}