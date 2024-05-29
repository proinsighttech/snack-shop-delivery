package com.proinsight.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "groups")
public class GroupModel extends RepresentationModel<GroupModel> {
    private Long id;

    private String name;
}

