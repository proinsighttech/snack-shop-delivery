package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class OrderItemInput {

    @NotNull
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private String observation;

}
