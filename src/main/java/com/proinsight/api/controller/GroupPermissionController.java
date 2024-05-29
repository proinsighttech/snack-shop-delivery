package com.proinsight.api.controller;

import com.proinsight.api.facade.GroupPermissionFacade;
import com.proinsight.api.model.PermissionModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private GroupPermissionFacade groupPermissionFacade;

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
        return groupPermissionFacade.list(groupId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removePermission(@PathVariable Long groupId, @PathVariable Long permissionId) {
        return groupPermissionFacade.removePermission(groupId, permissionId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addPermission(@PathVariable Long groupId, @PathVariable Long permissionId) {
        return groupPermissionFacade.addPermission(groupId, permissionId);
    }

}

