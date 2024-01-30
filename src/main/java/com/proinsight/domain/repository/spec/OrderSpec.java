package com.proinsight.domain.repository.spec;

import com.proinsight.domain.enums.OrderStatus;
import com.proinsight.domain.model.Order;
import com.proinsight.domain.repository.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpec {

    public static Specification<Order> usingFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("snackShop");
                root.fetch("client");
                if (filter.getStatus() != null) {
                    OrderStatus status = OrderStatus.fromString(filter.getStatus());
                    return builder.equal(root.get("status"), status);
                }
            }

            var predicates = new ArrayList<Predicate>();

            if (filter.getStatus() != null) {
                predicates.add(builder.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getClientId() != null) {
                predicates.add(builder.equal(root.get("client").get("id"), filter.getClientId()));
            }

            if (filter.getSnackShopId() != null) {
                predicates.add(builder.equal(root.get("snackShop"), filter.getSnackShopId()));
            }

            if (filter.getReceivedDateStart() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("receivedDate"),
                        filter.getReceivedDateStart()));
            }

            if (filter.getReceivedDateEnd() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("receivedDate"),
                        filter.getReceivedDateEnd()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
