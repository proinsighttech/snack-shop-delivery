package com.proinsight.domain.repository;

import com.proinsight.domain.model.IngredientStock;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IngredientStockRepository extends CustomJpaRepository<IngredientStock, Long>,
        JpaSpecificationExecutor<IngredientStock> {

    @Query("SELECT st FROM IngredientStock st JOIN st.ingredient i WHERE i.id = :ingredientId")
    Optional<IngredientStock> findByIngredientId(Long ingredientId);

    @Modifying
    @Transactional
    @Query("UPDATE IngredientStock st SET st.stockQuantity = st.stockQuantity + :quantity WHERE st.ingredient.id = :ingredientId")
    void updateStock(Long ingredientId, Integer quantity);

}

