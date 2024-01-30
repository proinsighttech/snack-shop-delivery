package com.proinsight.api.controller;

import com.proinsight.api.facade.OrderFlowFacade;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderFlowController {

    @Autowired
    private OrderFlowFacade orderFlowFacade;

    @CheckSecurity.Orders.PodeGerenciarPedidos
    @PutMapping("/prepare")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> prepare(@PathVariable Long orderCode) {
        return orderFlowFacade.prepare(orderCode);
    }

    @CheckSecurity.Orders.PodeGerenciarPedidos
    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable Long orderCode) {
        return orderFlowFacade.cancel(orderCode);
    }

    @CheckSecurity.Orders.PodeGerenciarPedidos
    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable Long orderCode) {
        return orderFlowFacade.confirm(orderCode);
    }

    @CheckSecurity.Orders.PodeGerenciarPedidos
    @PutMapping("/ready")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ready(@PathVariable Long orderCode) {
        return orderFlowFacade.ready(orderCode);
    }

    @CheckSecurity.Orders.PodeGerenciarPedidos
    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delivery(@PathVariable Long orderCode) {
        return orderFlowFacade.delivery(orderCode);
    }

}

