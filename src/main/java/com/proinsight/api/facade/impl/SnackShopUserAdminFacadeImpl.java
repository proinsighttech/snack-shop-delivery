package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.UserModelAssembler;
import com.proinsight.api.facade.SnackShopUserAdminFacade;
import com.proinsight.api.model.UserModel;
import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.service.SnackShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SnackShopUserAdminFacadeImpl implements SnackShopUserAdminFacade {

    @Autowired
    private SnackShopService snackShopService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    public CollectionModel<UserModel> list(Long snackShopId) {
        SnackShop snackShop = snackShopService.findOrThrow(snackShopId);
        CollectionModel<UserModel> userModels = userModelAssembler
                .toCollectionModel(snackShop.getAdmins());
        return userModels;
    }

    public ResponseEntity<Void> removeAdmin(Long snackShopId, Long userId) {
        snackShopService.removeAdmin(snackShopId, userId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> addAdmin(Long snackShopId, Long userId) {
        snackShopService.addAdmin(snackShopId, userId);
        return ResponseEntity.noContent().build();
    }

}