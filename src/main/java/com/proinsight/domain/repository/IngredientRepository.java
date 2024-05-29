package com.proinsight.domain.repository;

import com.proinsight.domain.model.Ingredient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientRepository extends CustomJpaRepository<Ingredient, Long>,
        JpaSpecificationExecutor<Ingredient> {
}

