package com.proinsight.api.controller;

import com.proinsight.api.facade.SnackShopUserAdminFacade;
import com.proinsight.api.model.UserModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/snackshops/{snackShopId}/admins")
public class SnackShopAdminUserController {

    @Autowired
    private SnackShopUserAdminFacade snackShopUserAdminFacade;

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UserModel> list(@PathVariable Long snackShopId) {
        return snackShopUserAdminFacade.list(snackShopId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeAdmin(@PathVariable Long snackShopId, @PathVariable Long userId) {
        return snackShopUserAdminFacade.removeAdmin(snackShopId, userId);
    }

    @CheckSecurity.UserGroupsPermissions.PodeEditar
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addAdmin(@PathVariable Long snackShopId, @PathVariable Long userId) {
        return snackShopUserAdminFacade.addAdmin(snackShopId, userId);
    }

}
