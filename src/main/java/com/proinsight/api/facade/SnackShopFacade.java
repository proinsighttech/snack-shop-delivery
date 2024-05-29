package com.proinsight.api.facade;

import com.proinsight.api.model.SnackShopModel;
import com.proinsight.api.model.input.SnackShopInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface SnackShopFacade {

    CollectionModel<SnackShopModel> list();

    SnackShopModel find(Long snackShopId);

    SnackShopModel addSnackShop(SnackShopInput snackShopInput);

    SnackShopModel updateSnackShop(Long snackShopId, SnackShopInput snackShopInput);

    ResponseEntity<Void> activeSnackShop(Long snackShopId);

    ResponseEntity<Void> inactiveSnackShop(Long snackShopId);


}
