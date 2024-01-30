package com.proinsight.api.assembler;

import com.proinsight.api.controller.SnackShopController;
import com.proinsight.api.model.SnackShopModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.SnackShop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class SnackShopModelAssembler
        extends RepresentationModelAssemblerSupport<SnackShop, SnackShopModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public SnackShopModelAssembler() {
        super(SnackShopController.class, SnackShopModel.class);
    }

    @Override
    public SnackShopModel toModel(SnackShop snackShop) {
        SnackShopModel snackShopModel = createModelWithId(snackShop.getId(), snackShop);
        modelMapper.map(snackShop, snackShopModel);

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            snackShopModel.add(snackShopLinks.linkToSnackShops("snackShops"));
        }

        if (snackShopSecurity.podeGerenciarCadastroLanchonetes()) {
            snackShopModel.add(
                    snackShopLinks.linkToSnackShopActive(snackShop.getId(), "active"));

            snackShopModel.add(
                    snackShopLinks.linkToSnackShopInactive(snackShop.getId(), "inactive"));
        }


//        if (snackShopSecurity.podeConsultarRestaurantes()) {
//            snackShopModel.add(snackShopLinks.linkToProducts("products"));
//        }


        if (snackShopSecurity.podeConsultarLanchonetes()) {
            snackShopModel.add(snackShopLinks.linkToPaymentMethods(snackShop.getId(),
                    "formas-pagamento"));
        }

        if (snackShopSecurity.podeGerenciarCadastroLanchonetes()) {
            snackShopModel.add(snackShopLinks.linkToSnackShopAdmins(snackShop.getId(),
                    "responsaveis"));
        }

        return snackShopModel;
    }


    @Override
    public CollectionModel<SnackShopModel> toCollectionModel(Iterable<? extends SnackShop> entities) {
        CollectionModel<SnackShopModel> collectionModel = super.toCollectionModel(entities);

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            collectionModel.add(snackShopLinks.linkToSnackShops());
        }


        return collectionModel;
    }
}
