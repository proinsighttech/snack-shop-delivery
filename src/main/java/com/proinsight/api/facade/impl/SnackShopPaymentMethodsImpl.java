package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.PaymentMethodModelAssembler;
import com.proinsight.api.facade.SnackShopPaymentMethodsFacade;
import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.service.SnackShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SnackShopPaymentMethodsImpl implements SnackShopPaymentMethodsFacade {

    @Autowired
    private SnackShopService snackShopService;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    public CollectionModel<PaymentMethodModel> list(Long snackshopId) {
        SnackShop snackShop = snackShopService.findOrThrow(snackshopId);

        CollectionModel<PaymentMethodModel> formasPagamentoModel
                = paymentMethodModelAssembler.toCollectionModel(snackShop.getPaymentMethods());
        return formasPagamentoModel;
    }

    public ResponseEntity<Void> removePaymentMethod(Long snackshopId, Long paymentMethodId) {
        snackShopService.removePaymentMethod(snackshopId, paymentMethodId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> addPaymentMethod(Long snackshopId, Long paymentMethodId) {
        snackShopService.addPaymentMethod(snackshopId, paymentMethodId);
        return ResponseEntity.noContent().build();
    }

}