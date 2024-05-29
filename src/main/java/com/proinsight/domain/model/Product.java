package com.proinsight.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proinsight.domain.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "snack_shop_id", nullable = false)
    private SnackShop snackShop;

    @ManyToMany
    @JoinTable(name = "product_ingredient", joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public boolean removeIngredient(Ingredient ingredient) {
        return getIngredients().remove(ingredient);
    }

    public boolean addIngredient(Ingredient ingredient) {
        return getIngredients().add(ingredient);
    }

}
