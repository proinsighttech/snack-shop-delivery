package com.proinsight.api.facade;

import com.proinsight.api.model.UserModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface SnackShopUserAdminFacade {

    CollectionModel<UserModel> list(Long snackShopId);

    ResponseEntity<Void> removeAdmin(Long snackShopId, Long userId);

    ResponseEntity<Void> addAdmin(Long snackShopId, Long userId);

}
