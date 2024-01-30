package com.proinsight.api.assembler;

import com.proinsight.api.model.PermissionModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissionModelAssembler
        implements RepresentationModelAssembler<Permission, PermissionModel> {

        @Autowired
        private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;


    @Override
        public PermissionModel toModel(Permission permission) {
            PermissionModel permissionModel = modelMapper.map(permission, PermissionModel.class);
        return permissionModel;
    }

        @Override
        public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
        CollectionModel<PermissionModel> collectionModel
                = RepresentationModelAssembler.super.toCollectionModel(entities);

            if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
                collectionModel.add(snackShopLinks.linkToPermissions());
            }

        return collectionModel;
    }
    }

