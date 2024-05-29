package com.proinsight.domain.listener;

import com.proinsight.domain.event.ReceiveOrderEvent;
import com.proinsight.domain.service.OrderFlowService;
import com.proinsight.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ReceiveOrderListener {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFlowService orderFlowService;

    @TransactionalEventListener
    public void whenReceiveOrder(ReceiveOrderEvent event) {
        Long orderId = event.getOrder().getId();
        new Thread(() -> {
            String paymentResponse = fakeMercadoPagoIntegration(orderId);
            System.out.println(paymentResponse);
            if (paymentResponse.contains("successful")) {
                orderFlowService.confirm(event.getOrder().getId());
            }
            else {
                orderFlowService.cancel(event.getOrder().getId());
            }
        }).start();
    }

    private String fakeMercadoPagoIntegration(Long orderId) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Payment for order " + orderId + " was successful in Mercado Pago.";
    }
}
