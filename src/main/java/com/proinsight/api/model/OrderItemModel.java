package com.proinsight.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private String observation;

    private List<IngredientModel> productIngredients;

}
