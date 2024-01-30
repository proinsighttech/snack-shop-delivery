package com.proinsight.domain.service;

import com.proinsight.domain.exception.IngredientNotFoundException;
import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Transactional
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    public Ingredient findOrThrow(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));
    }
}