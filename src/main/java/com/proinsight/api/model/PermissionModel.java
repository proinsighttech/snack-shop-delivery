package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "permissions")
public class PermissionModel extends RepresentationModel<PermissionModel> {

    private Long id;

    private String name;

    private String description;

}
