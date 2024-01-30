package com.proinsight.api.facade;

import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.api.model.input.PaymentMethodInput;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

public interface PaymentMethodFacade {

    ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request);

    ResponseEntity<PaymentMethodModel> find(Long paymentMethodId, ServletWebRequest request);

    PaymentMethodModel addPaymentMethod(PaymentMethodInput paymentMethodInput);

    PaymentMethodModel updatePaymentMethod(Long paymentMethodId, PaymentMethodInput paymentMethodInput);

    void remove(Long paymentMethodId);

}
