package com.proinsight.api.facade.impl;

import com.proinsight.api.facade.OrderFlowFacade;
import com.proinsight.domain.service.OrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderFlowFacadeImpl implements OrderFlowFacade {

    @Autowired
    private OrderFlowService orderFlowService;

    public ResponseEntity<Void> receive(Long orderCode) {
        orderFlowService.receive(orderCode);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> prepare(Long orderCode) {
        orderFlowService.prepare(orderCode);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> cancel(Long orderCode) {
        orderFlowService.cancel(orderCode);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> confirm(Long orderCode) {
        orderFlowService.confirm(orderCode);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> ready(Long orderCode) {
        orderFlowService.ready(orderCode);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> delivery(Long orderCode) {
        orderFlowService.delivery(orderCode);
        return ResponseEntity.noContent().build();
    }

}