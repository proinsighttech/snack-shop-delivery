package com.proinsight.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderInput {

    @Valid
    private UserCpfInput client;

    @Valid
    @NotNull
    private SnackShopIdInput snackShop;

    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemInput> items;
}
