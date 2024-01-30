package com.proinsight.domain.repository;

import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.model.ProductIngredient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductIngredientRepository extends CustomJpaRepository<ProductIngredient, Long>,
        JpaSpecificationExecutor<ProductIngredient> {

    @Query("from ProductIngredient pi join fetch pi.product p where pi.id = :productId")
    List<Ingredient> findAllIngredientByProduct(Long productId);

    @Query("from ProductIngredient pi join fetch pi.ingredient p where pi.id = :ingredientId")
    Optional<ProductIngredient> findByIngredientId(Long ingredientId);

}

