package com.proinsight.api.assembler;

import com.proinsight.api.controller.UserController;
import com.proinsight.api.model.UserModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler
        extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user) {
        UserModel userModel = createModelWithId(user.getId(), user);
        modelMapper.map(user, userModel);

        if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
            userModel.add(snackShopLinks.linkToUsers("users"));

            userModel.add(snackShopLinks.linkToUserGroups(user.getId(), "user-groups"));
        }

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities)
                .add(snackShopLinks.linkToUsers());
    }
}
