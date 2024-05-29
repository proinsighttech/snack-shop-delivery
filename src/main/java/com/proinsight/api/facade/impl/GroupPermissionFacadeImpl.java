package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.PermissionModelAssembler;
import com.proinsight.api.facade.GroupPermissionFacade;
import com.proinsight.api.model.PermissionModel;
import com.proinsight.domain.model.Group;
import com.proinsight.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupPermissionFacadeImpl implements GroupPermissionFacade {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    public CollectionModel<PermissionModel> list(Long groupId) {
        Group group = groupService.findOrThrow(groupId);
        CollectionModel<PermissionModel> permissionsModel
                = permissionModelAssembler.toCollectionModel(group.getPermissions());
        return permissionsModel;
    }

    public ResponseEntity<Void> removePermission(Long groupId, Long permissionId) {
        groupService.removePermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> addPermission(Long groupId, Long permissionId) {
        groupService.addPermission(groupId, permissionId);
        return ResponseEntity.noContent().build();
    }
}