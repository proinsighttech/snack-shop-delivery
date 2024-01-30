package com.proinsight.api.facade;

import com.proinsight.api.model.IngredientModel;
import com.proinsight.api.model.IngredientStockModel;
import com.proinsight.api.model.input.IngredientInput;
import com.proinsight.domain.repository.filter.IngredientFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface IngredientFacade {

    PagedModel<IngredientStockModel> findAll(IngredientFilter filter, Pageable pageable);

    IngredientStockModel findIngredient(Long ingredientId);

    IngredientModel addIngredient(IngredientInput ingredientInput);

    IngredientModel updateIngredient(Long ingredientId, IngredientInput ingredientInput);

    void deleteIngredient(Long ingredientId);
}
