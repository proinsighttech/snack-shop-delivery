package com.proinsight.api.model;

import com.proinsight.domain.model.PaymentMethod;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.HashSet;
import java.util.Set;

@Data
@Relation(collectionRelation = "snackShops")
public class SnackShopModel extends RepresentationModel<SnackShopModel> {

    private Long id;
    private String name;
    private Boolean active;
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

}
