package com.proinsight.domain.enums;

import java.util.List;

public enum ProductCategory {

    SNACK("Lanche"),
    SIDE_DISH("Acompanhamento"),
    DRINK("Bebida"),
    DESSERT("Sobremesa");

    private String description;
    private List<OrderStatus> previousStatus;

    ProductCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static ProductCategory fromString(String category) {
        for (ProductCategory productCategory : ProductCategory.values()) {
            if (productCategory.name().toUpperCase().contains(category.toUpperCase()) ||
                    productCategory.description.toUpperCase().contains(category.toUpperCase())) {
                return productCategory;
            }

        }
        return null;
    }

}
