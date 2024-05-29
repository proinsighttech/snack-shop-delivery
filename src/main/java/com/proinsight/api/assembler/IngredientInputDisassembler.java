package com.proinsight.api.assembler;

import com.proinsight.api.model.input.IngredientInput;
import com.proinsight.domain.model.Ingredient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Ingredient toDomainObject(IngredientInput ingredientInput) {
        return modelMapper.map(ingredientInput, Ingredient.class);
    }

    public void copyToDomainObject(IngredientInput ingredientInput, Ingredient ingredient) {
        modelMapper.map(ingredientInput, ingredient);
    }

}

