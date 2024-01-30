package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.IngredientModelAssembler;
import com.proinsight.api.facade.ProductIngredientFacade;
import com.proinsight.api.model.IngredientModel;
import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.repository.ProductIngredientRepository;
import com.proinsight.domain.service.ProductIngredientService;
import com.proinsight.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductIngredientFacadeImpl implements ProductIngredientFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductIngredientService productIngredientService;

    @Autowired
    private ProductIngredientRepository productIngredientRepository;

    @Autowired
    private IngredientModelAssembler ingredientModelAssembler;

    public CollectionModel<IngredientModel> listAllIngredients(Long productId) {
        Product product = productService.findOrThrow(productId);
        List<Ingredient> ingredients = productIngredientRepository.findAllIngredientByProduct(productId);
        return ingredientModelAssembler.toCollectionModel(ingredients);
    }

    public ResponseEntity<Void> addIngredientToProduct(Long productId, Long ingredientId) {
        productIngredientService.addIngredient(productId, ingredientId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> removeIngredientToProduct(Long productId, Long ingredientId) {
        productIngredientService.removeIngredient(productId, ingredientId);
        return ResponseEntity.noContent().build();
    }

}