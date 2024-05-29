package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class IngredientModel extends RepresentationModel<IngredientModel> {

    private String ingredientName;

}
