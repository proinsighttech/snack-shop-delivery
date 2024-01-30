package com.proinsight.api.facade;

import com.proinsight.api.model.IngredientModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface ProductIngredientFacade {

    CollectionModel<IngredientModel> listAllIngredients(Long productId);

    ResponseEntity<Void> addIngredientToProduct(Long productId, Long ingredientId);

    ResponseEntity<Void> removeIngredientToProduct(Long productId, Long ingredientId);

}
