package com.proinsight.domain.listener;

import com.proinsight.domain.event.PreparingOrderEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PrepareOrderListener {

    @TransactionalEventListener
    public void whenPreparingOrder(PreparingOrderEvent event) {
    }
}
