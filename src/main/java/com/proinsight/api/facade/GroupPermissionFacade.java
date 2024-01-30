package com.proinsight.api.facade;

import com.proinsight.api.model.PermissionModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface GroupPermissionFacade {

    CollectionModel<PermissionModel> list(Long groupId);

    ResponseEntity<Void> removePermission(Long groupId, Long permissionId);

    ResponseEntity<Void> addPermission(Long groupId, Long permissionId);
}
