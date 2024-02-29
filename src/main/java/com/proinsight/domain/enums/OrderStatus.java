package com.proinsight.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum OrderStatus {

    CREATED("Criado",4),
    RECEIVED("Recebido",3, CREATED),
    CANCELED("Cancelado",7, RECEIVED),
    CONFIRMED("Confirmado",6, RECEIVED),
    IN_PROGRESS("Em Preparação",2, CONFIRMED),
    READY("Pronto",1, IN_PROGRESS),
    DELIVERED("Entregue",5, READY);

    private String description;
    private List<OrderStatus> previousStatus;
    private int order;

    OrderStatus(String description,  int order, OrderStatus... previousStatus) {
        this.description = description;
        this.order = order;
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