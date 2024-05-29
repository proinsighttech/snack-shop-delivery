package com.proinsight.domain.service;

import com.proinsight.domain.exception.IngredientNotFoundException;
import com.proinsight.domain.exception.ProductNotFoundException;
import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.model.ProductIngredient;
import com.proinsight.domain.repository.ProductIngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductIngredientService {

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductIngredientRepository productIngredientRepository;

    @Transactional
    public ProductIngredient save(ProductIngredient productIngredient) {
        return productIngredientRepository.save(productIngredient);
    }


    public ProductIngredient findOrThrow(Long productId) {
        return productIngredientRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public ProductIngredient findByIngredientIdOrThrow(Long ingredientId) {
        return productIngredientRepository.findByIngredientId(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));
    }

    @Transactional
    public void addIngredient(Long productId, Long ingredientId) {
        Product product = productService.findOrThrow(productId);
        Ingredient ingredient = ingredientService.findOrThrow(ingredientId);
        product.addIngredient(ingredient);
    }

    @Transactional
    public void removeIngredient(Long productId, Long ingredientId) {
        Product product = productService.findOrThrow(productId);
        Ingredient ingredient = ingredientService.findOrThrow(ingredientId);
        product.removeIngredient(ingredient);
    }

}
