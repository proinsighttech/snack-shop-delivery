package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "paymentMethods")
public class PaymentMethodModel extends RepresentationModel<PaymentMethodModel> {

    private Long id;
    private String description;

}
