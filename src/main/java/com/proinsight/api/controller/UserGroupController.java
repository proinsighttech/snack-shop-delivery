package com.proinsight.api.controller;

import com.proinsight.api.facade.UserGroupFacade;
import com.proinsight.api.model.GroupModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private UserGroupFacade userGroupFacade;

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GroupModel> listAllGroup(@PathVariable Long userId) {
        return userGroupFacade.listAllGroup(userId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return userGroupFacade.removeGroup(userId, groupId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        return userGroupFacade.addGroup(userId, groupId);
    }

}