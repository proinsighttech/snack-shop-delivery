package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class IngredientStockModel extends RepresentationModel<IngredientStockModel> {

    private String ingredientName;

    private String ingredientDescription;

    private String stockQuantity;
}
