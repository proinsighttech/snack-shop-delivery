package com.proinsight.domain.service;

import com.proinsight.domain.exception.BusinessException;
import com.proinsight.domain.exception.IngredientNotFoundException;
import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.model.IngredientStock;
import com.proinsight.domain.model.OrderItem;
import com.proinsight.domain.model.ProductIngredient;
import com.proinsight.domain.repository.IngredientStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IngredientStockService {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductIngredientService productIngredientService;

    @Autowired
    private IngredientStockRepository ingredientStockRepository;


    public IngredientStock findOrThrow(Long ingredientId) {
        return ingredientStockRepository.findByIngredientId(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));
    }

    @Transactional
    public void subtractIngredientsFromStock(List<OrderItem> orderItems) {
        orderItems.stream()
                .flatMap(orderItem -> orderItem.getProduct().getIngredients().stream())
                .forEach(this::subtractIngredient);
    }

    @Transactional
    public void addIngredientsOfStock(List<OrderItem> orderItems) {
        orderItems.stream()
                .flatMap(orderItem -> orderItem.getProduct().getIngredients().stream())
                .forEach(this::addIngredient);
    }

    public void subtractIngredient(Ingredient ingredient) {
        ProductIngredient productIngredient = productIngredientService.findByIngredientIdOrThrow(ingredient.getId());
        Long ingredientId = ingredient.getId();
        Integer quantity = productIngredient.getQuantity();

        IngredientStock stock = findOrThrow(ingredientId);
        Integer stockQuantity = stock.getStockQuantity();

        if (stockQuantity < quantity || stockQuantity == 0) {
            throw new BusinessException(
                    String.format("Quantidade de %s insuficiente em estoque. Quantidade em estoque: %d", ingredient.getName(), stockQuantity));
        }
        ingredientStockRepository.updateStock(ingredientId, stockQuantity - quantity);
        ingredientStockRepository.saveAndFlush(stock);
    }

    public void addIngredient(Ingredient ingredient) {
        ProductIngredient productIngredient = productIngredientService.findByIngredientIdOrThrow(ingredient.getId());
        Long ingredientId = ingredient.getId();
        Integer quantity = productIngredient.getQuantity();

        IngredientStock stock = findOrThrow(ingredientId);
        Integer stockQuantity = stock.getStockQuantity();

        ingredientStockRepository.updateStock(ingredientId, stockQuantity + quantity);
        ingredientStockRepository.saveAndFlush(stock);
    }
}