package com.proinsight.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "users")
public class UserModel extends RepresentationModel<UserModel> {

    private Long id;

    private String name;

    private String cpf;

    private String email;

}
