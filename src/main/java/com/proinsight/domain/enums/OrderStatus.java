package com.proinsight.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CREATED("Criado"),
    RECEIVED("Recebido", CREATED),
    CANCELED("Cancelado", RECEIVED),
    CONFIRMED("Confirmado", RECEIVED),
    IN_PROGRESS("Em Preparação", CONFIRMED),
    READY("Pronto", IN_PROGRESS),
    DELIVERED("Entregue", READY);

    @Getter
    private String description;
    private List<OrderStatus> previousStatus;

    OrderStatus(String description, OrderStatus... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public boolean cannotChangeTo(OrderStatus newStatus){
        return !newStatus.previousStatus.contains(this);
    }

    public boolean canChangeTo(OrderStatus newStatus) {
        return !cannotChangeTo(newStatus);
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().toUpperCase().contains(status.toUpperCase()) ||
                    orderStatus.description.toUpperCase().contains(status.toUpperCase())) {
                return orderStatus;
            }

        }
        return null;
    }
}