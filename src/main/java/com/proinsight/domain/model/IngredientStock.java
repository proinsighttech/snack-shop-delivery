package com.proinsight.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "ingredient_stock")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IngredientStock {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;
}