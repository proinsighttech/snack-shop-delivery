package com.proinsight.domain.event;

import com.proinsight.domain.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreparingOrderEvent {

    private Order order;

    public PreparingOrderEvent(Order order) {
        this.order = order;
    }

}
