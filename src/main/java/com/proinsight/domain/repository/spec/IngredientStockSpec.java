package com.proinsight.domain.repository.spec;

import com.proinsight.domain.model.IngredientStock;
import com.proinsight.domain.repository.filter.IngredientFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class IngredientStockSpec {

    public static Specification<IngredientStock> usingFilter(IngredientFilter filter) {
        return (root, query, builder) -> {
            if (IngredientStock.class.equals(query.getResultType())) {
                root.fetch("ingredient");
            }

            var predicates = new ArrayList<Predicate>();

            if (filter.getIngredientName()!= null) {
                predicates.add(builder.like(root.get("ingredient").get("name"), filter.getIngredientName()));
            }

            if (filter.getStockQuantity()!= null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("stockQuantity"), filter.getStockQuantity()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
