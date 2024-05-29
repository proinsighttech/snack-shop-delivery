package com.proinsight.api.assembler;

import com.proinsight.api.controller.IngredientController;
import com.proinsight.api.model.IngredientModel;
import com.proinsight.domain.model.Ingredient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {
    @Autowired
    private ModelMapper modelMapper;

    public IngredientModelAssembler() {
        super(IngredientController.class, IngredientModel.class);
    }

    @Override
    public IngredientModel toModel(Ingredient ingredient) {
        IngredientModel ingredientModel = createModelWithId(ingredient.getId(), ingredient);
        modelMapper.map(ingredient, ingredientModel);
        return ingredientModel;
    }
}
