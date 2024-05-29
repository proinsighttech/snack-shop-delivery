package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "images")
public class ProductImageModel extends RepresentationModel<ProductImageModel> {

    private String fileName;

    private String description;

    private String contentType;

    private Long size;

}