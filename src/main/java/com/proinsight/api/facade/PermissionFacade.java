package com.proinsight.api.facade;

import com.proinsight.api.model.PermissionModel;
import org.springframework.hateoas.CollectionModel;

public interface PermissionFacade {
    CollectionModel<PermissionModel> list();
}
