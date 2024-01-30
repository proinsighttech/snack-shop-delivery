package com.proinsight.api.model;

import com.proinsight.domain.enums.ProductCategory;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.List;

@Data
@Relation(collectionRelation = "products")
public class ProductModel extends RepresentationModel<ProductModel> {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private Boolean active;

    private List<IngredientModel> ingredients;

    public void setCategory(String category) {
        this.category = ProductCategory.valueOf(category).getDescription();
    }
}