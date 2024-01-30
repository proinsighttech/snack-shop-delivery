package com.proinsight.api.controller;

import com.proinsight.api.facade.SnackShopPaymentMethodsFacade;
import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/snackshops/{snackshopId}/payment-methods")
public class SnackShopPaymentMethodsController {

    @Autowired
    private SnackShopPaymentMethodsFacade snackShopPaymentMethodsFacade;

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PaymentMethodModel> list(@PathVariable Long snackshopId) {
        return snackShopPaymentMethodsFacade.list(snackshopId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removePaymentMethod(@PathVariable Long snackshopId, @PathVariable Long paymentMethodId) {
        return snackShopPaymentMethodsFacade.removePaymentMethod(snackshopId, paymentMethodId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addPaymentMethod(@PathVariable Long snackshopId, @PathVariable Long paymentMethodId) {
        return snackShopPaymentMethodsFacade.addPaymentMethod(snackshopId, paymentMethodId);
    }

}

