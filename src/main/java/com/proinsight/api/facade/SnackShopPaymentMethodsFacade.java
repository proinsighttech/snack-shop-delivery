package com.proinsight.api.facade;

import com.proinsight.api.model.PaymentMethodModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface SnackShopPaymentMethodsFacade {

    CollectionModel<PaymentMethodModel> list(Long snackshopId);

    ResponseEntity<Void> removePaymentMethod(Long snackshopId, Long paymentMethodId);

    ResponseEntity<Void> addPaymentMethod(Long snackshopId, Long paymentMethodId);

}
