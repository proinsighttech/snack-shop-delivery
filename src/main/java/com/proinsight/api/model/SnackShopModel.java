package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "snackShops")
public class SnackShopModel extends RepresentationModel<SnackShopModel> {

    private Long id;
    private String name;
    private Boolean active;

}
