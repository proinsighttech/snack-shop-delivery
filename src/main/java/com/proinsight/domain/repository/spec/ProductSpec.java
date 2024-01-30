package com.proinsight.domain.repository.spec;

import com.proinsight.domain.enums.ProductCategory;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.repository.filter.ProductFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class ProductSpec {

    public static Specification<Product> usingFilter(ProductFilter filter) {
        return (root, query, builder) -> {
            if (Product.class.equals(query.getResultType())) {
                root.fetch("snackShop");
                if (filter.getCategory() != null) {
                    ProductCategory category = ProductCategory.fromString(filter.getCategory());
                    return builder.equal(root.get("category"), category);
                }
            }

            var predicates = new ArrayList<Predicate>();

            if (filter.getCategory() != null) {
                predicates.add(builder.equal(root.get("category"), filter.getCategory()));
            }

            if (filter.getSnackShopId() != null) {
                predicates.add(builder.equal(root.get("snackShop").get("id"), filter.getSnackShopId()));
            }

            if (filter.getActive() != null) {
                predicates.add(builder.equal(root.get("active"), filter.getActive()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
