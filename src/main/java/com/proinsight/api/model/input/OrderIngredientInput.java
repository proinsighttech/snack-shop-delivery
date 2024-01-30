package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderIngredientInput {

    @NotBlank
    private String ingredientId;

}
