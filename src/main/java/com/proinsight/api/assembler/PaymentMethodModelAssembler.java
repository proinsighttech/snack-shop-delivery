package com.proinsight.api.assembler;

import com.proinsight.api.controller.PaymentMethodController;
import com.proinsight.api.model.PaymentMethodModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodModelAssembler
        extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public PaymentMethodModelAssembler() {
        super(PaymentMethodController.class, PaymentMethodModel.class);
    }

    @Override
    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel =
            createModelWithId(paymentMethod.getId(), paymentMethod);
        modelMapper.map(paymentMethod, paymentMethodModel);

        if (snackShopSecurity.podeConsultarFormasPagamento()) {
            paymentMethodModel.add(snackShopLinks.linkToPaymentMethods("paymentMethod"));
        }

        return paymentMethodModel;
    }

    @Override
    public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> entities) {

        CollectionModel<PaymentMethodModel> collectionModel = super.toCollectionModel(entities);

        if (snackShopSecurity.podeConsultarFormasPagamento()) {
            collectionModel.add(snackShopLinks.linkToPaymentMethods());
        }

        return collectionModel;
    }
}
