package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.PaymentMethodInputDisassembler;
import com.proinsight.api.assembler.PaymentMethodModelAssembler;
import com.proinsight.api.facade.PaymentMethodFacade;
import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.api.model.input.PaymentMethodInput;
import com.proinsight.domain.model.PaymentMethod;
import com.proinsight.domain.repository.PaymentMethodRepository;
import com.proinsight.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PaymentMethodFacadeImpl implements PaymentMethodFacade {


    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Autowired
    private PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    public ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        List<PaymentMethod> todasFormasPagamentos = paymentMethodRepository.findAll();
        CollectionModel<PaymentMethodModel> formasPagamentosModel = paymentMethodModelAssembler
                .toCollectionModel(todasFormasPagamentos);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .body(formasPagamentosModel);
    }

    public ResponseEntity<PaymentMethodModel> find(Long paymentMethodId,
                                                   ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        PaymentMethod paymentMethod = paymentMethodService.findOrThrow(paymentMethodId);
        PaymentMethodModel paymentMethodModel = paymentMethodModelAssembler.toModel(paymentMethod);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentMethodModel);
    }

    public PaymentMethodModel addPaymentMethod(PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);
        paymentMethod = paymentMethodService.save(paymentMethod);
        return paymentMethodModelAssembler.toModel(paymentMethod);
    }

    public PaymentMethodModel updatePaymentMethod(Long paymentMethodId, PaymentMethodInput paymentMethodInput) {
        PaymentMethod currentPaymentMethod = paymentMethodService.findOrThrow(paymentMethodId);
        paymentMethodInputDisassembler.copyToDomainObject(paymentMethodInput, currentPaymentMethod);
        currentPaymentMethod = paymentMethodService.save(currentPaymentMethod);
        return paymentMethodModelAssembler.toModel(currentPaymentMethod);
    }

    public void remove(Long paymentMethodId) {
        paymentMethodService.delete(paymentMethodId);
    }

}