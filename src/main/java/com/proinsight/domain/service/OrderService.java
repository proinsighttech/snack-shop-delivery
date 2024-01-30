package com.proinsight.domain.service;

import com.proinsight.domain.exception.BusinessException;
import com.proinsight.domain.exception.OrderNotFoundException;
import com.proinsight.domain.model.*;
import com.proinsight.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SnackShopService snackShopService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    public Order findOrThrow(Long orderCode) {
        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException(orderCode));
    }

    @Transactional
    public void saveAndFlush(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Transactional
    public Order create(Order order) {
        validateOrder(order);
        validarItems(order);
        order.calculateTotal();
        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        User client = userService.findOrThrow(order.getClient().getId());
        SnackShop snackShop = snackShopService.findOrThrow(order.getSnackShop().getId());
        PaymentMethod paymentMethod = paymentMethodService.findOrThrow(order.getPaymentMethod().getId());

        order.setClient(client);
        order.setSnackShop(snackShop);
        order.setPaymentMethod(paymentMethod);

        if (snackShop.cannotAcceptPaymentMethod(paymentMethod)) {
            throw new BusinessException(String.format("Forma de pagamento '%s' não é aceita por essa lanchonete.",
                    paymentMethod.getDescription()));
        }
    }

    private void validarItems(Order order) {
        order.getItems().forEach(item -> {
            Product product = productService.findOrThrow(
                    order.getSnackShop().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }
}