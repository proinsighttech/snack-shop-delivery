package com.proinsight.api.controller;

import com.proinsight.api.facade.PaymentMethodFacade;
import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.api.model.input.PaymentMethodInput;
import com.proinsight.api.security.CheckSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodFacade paymentMethodFacade;

    @CheckSecurity.PaymentMethods.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request) {
        return paymentMethodFacade.list(request);
    }

    @CheckSecurity.PaymentMethods.PodeConsultar
    @GetMapping(value = "/{paymentMethodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentMethodModel> find(@PathVariable Long paymentMethodId,
                                                      ServletWebRequest request) {
        return paymentMethodFacade.find(paymentMethodId, request);
    }

    @CheckSecurity.PaymentMethods.PodeEditar
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentMethodModel addPaymentMethod(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        return paymentMethodFacade.addPaymentMethod(paymentMethodInput);
    }

    @CheckSecurity.PaymentMethods.PodeEditar
    @PutMapping(value = "/{paymentMethodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentMethodModel updatePaymentMethod(@PathVariable Long paymentMethodId,
                                         @RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        return paymentMethodFacade.updatePaymentMethod(paymentMethodId, paymentMethodInput);
    }

    @CheckSecurity.PaymentMethods.PodeEditar
    @DeleteMapping(value = "/{paymentMethodId}", produces = {})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long paymentMethodId) {
        paymentMethodFacade.remove(paymentMethodId);
    }

}
