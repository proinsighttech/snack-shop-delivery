package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.PermissionModelAssembler;
import com.proinsight.api.facade.PermissionFacade;
import com.proinsight.api.model.PermissionModel;
import com.proinsight.domain.model.Permission;
import com.proinsight.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionFacadeImpl implements PermissionFacade {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    public CollectionModel<PermissionModel> list() {
        List<Permission> allPermissions = permissionRepository.findAll();
        return permissionModelAssembler.toCollectionModel(allPermissions);
    }

}