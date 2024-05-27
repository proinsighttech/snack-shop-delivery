package com.proinsight.api.facade;

import com.proinsight.api.model.IngredientModel;
import com.proinsight.api.model.IngredientStockModel;
import com.proinsight.api.model.input.IngredientInput;
import com.proinsight.domain.model.IngredientStock;
import com.proinsight.domain.model.ProductIngredient;
import com.proinsight.domain.repository.filter.IngredientFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface IngredientFacade {

    PagedModel<IngredientStockModel> findAll(IngredientFilter filter, Pageable pageable);

    IngredientStockModel findIngredient(Long ingredientId);

    IngredientModel addIngredient(IngredientInput ingredientInput);

    IngredientModel updateIngredient(Long ingredientId, IngredientInput ingredientInput);

    void deleteIngredient(Long ingredientId);

    ProductIngredient findProductIngredient(Long productId);

    IngredientStock findIngredientStock(Long ingredientId);

    void updateStock(Long ingredientId, Integer quantity);

}
