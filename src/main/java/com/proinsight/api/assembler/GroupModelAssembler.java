package com.proinsight.api.assembler;

import com.proinsight.api.controller.GroupController;
import com.proinsight.api.model.GroupModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GroupModelAssembler
        extends RepresentationModelAssemblerSupport<Group, GroupModel> {

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private SnackShopLinks snackShopLinks;

        @Autowired
        private SnackShopSecurity snackShopSecurity;

        public GroupModelAssembler() {
            super(GroupController.class, GroupModel.class);
        }

        @Override
        public GroupModel toModel(Group group) {
            GroupModel groupModel = createModelWithId(group.getId(), group);
            modelMapper.map(group, groupModel);

            if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
                groupModel.add(snackShopLinks.linkToGroups("groups"));

                groupModel.add(snackShopLinks.linkToPermissionsGroup(group.getId(), "permissions"));
            }

            return groupModel;
        }

            @Override
            public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
            CollectionModel<GroupModel> collectionModel = super.toCollectionModel(entities);

                if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
                    collectionModel.add(snackShopLinks.linkToGroups());
                }

            return collectionModel;
        }
}