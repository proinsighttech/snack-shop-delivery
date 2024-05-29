package com.proinsight.api.controller;

import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (snackShopSecurity.podePesquisarPedidos()) {
            rootEntryPointModel.add(snackShopLinks.linkToOrders("orders"));
        }

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            rootEntryPointModel.add(snackShopLinks.linkToSnackShops("snackshops"));
        }

        if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModel.add(snackShopLinks.linkToGroups("groups"));
            rootEntryPointModel.add(snackShopLinks.linkToUsers("users"));
            rootEntryPointModel.add(snackShopLinks.linkToPermissions("permissions"));
        }

        if (snackShopSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointModel.add(snackShopLinks.linkToPaymentMethods("payment-methods"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
    }
}
