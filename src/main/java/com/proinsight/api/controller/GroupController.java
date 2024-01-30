package com.proinsight.api.controller;

import com.proinsight.api.facade.GroupFacade;
import com.proinsight.api.model.GroupModel;
import com.proinsight.api.model.input.GroupInput;
import com.proinsight.api.security.CheckSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private GroupFacade groupFacade;

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GroupModel> listAll() {
        return groupFacade.listAll();
    }

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping(path = "/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupModel findGroup(@PathVariable Long groupId) {
        return groupFacade.findGroup(groupId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel addGroup(@RequestBody @Valid GroupInput groupInput) {
        return groupFacade.addGroup(groupInput);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @PutMapping(path = "/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupModel updateGroup(@PathVariable Long groupId,
                                @RequestBody @Valid GroupInput groupInput) {
        return groupFacade.updateGroup(groupId, groupInput);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGroup(@PathVariable Long groupId) {
        groupFacade.removeGroup(groupId);
    }

}