package com.proinsight.api.assembler;

import com.proinsight.api.controller.IngredientController;
import com.proinsight.api.model.IngredientStockModel;
import com.proinsight.domain.model.IngredientStock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class IngredientResumeModelAssembler
        extends RepresentationModelAssemblerSupport<IngredientStock, IngredientStockModel> {

    @Autowired
    private ModelMapper modelMapper;

    public IngredientResumeModelAssembler() {
        super(IngredientController.class, IngredientStockModel.class);
    }

    @Override
    public IngredientStockModel toModel(IngredientStock ingredientStock) {
        IngredientStockModel ingredientStockModel = createModelWithId(ingredientStock.getId(), ingredientStock);
        modelMapper.map(ingredientStock, ingredientStockModel);
        return ingredientStockModel;
    }

}

