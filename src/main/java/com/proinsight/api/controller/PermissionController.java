package com.proinsight.api.controller;

import com.proinsight.api.facade.PermissionFacade;
import com.proinsight.api.model.PermissionModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {

    @Autowired
    private PermissionFacade permissionFacade;

    @CheckSecurity.UserGroupsPermissions.PodeConsultar
    @GetMapping
    public CollectionModel<PermissionModel> list() {
        return permissionFacade.list();
    }
}
