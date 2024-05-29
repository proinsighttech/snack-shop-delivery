package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class IngredientProductModel extends RepresentationModel<IngredientProductModel> {

    private String productName;

    private List<String> ingredientName;

}
