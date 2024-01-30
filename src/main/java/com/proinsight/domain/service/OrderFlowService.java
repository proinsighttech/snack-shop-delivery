package com.proinsight.domain.service;

import com.proinsight.domain.model.Order;
import com.proinsight.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFlowService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientStockService ingredientStockService;

    @Transactional
    public void receive(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        ingredientStockService.subtractIngredientsFromStock(order.getItems());
        order.receive();
        orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void confirm(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        order.confirm();
        orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void prepare(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        order.prepare();
        orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void cancel(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        ingredientStockService.addIngredientsOfStock(order.getItems());
        order.cancel();
        orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void ready(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        order.ready();
    }

    @Transactional
    public void delivery(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        order.delivery();
    }

}