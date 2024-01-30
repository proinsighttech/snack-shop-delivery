package com.proinsight.api.assembler;

import com.proinsight.api.controller.OrderController;
import com.proinsight.api.model.OrderModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler
        extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;


    public OrderModelAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    @Override
    public OrderModel toModel(Order order) {
        OrderModel orderModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderModel);

        if (snackShopSecurity.podePesquisarPedidos()) {
            orderModel.add(snackShopLinks.linkToOrders("orders"));
        }

        if (snackShopSecurity.podeGerenciarPedidos(order.getCode())) {
            if (order.canBeConfirmed()) {
                orderModel.add(snackShopLinks.linkToOrderConfirm(order.getCode(), "confirm"));
            }

            if (order.canBeCanceled()) {
                orderModel.add(snackShopLinks.linkToOrderCancel(order.getCode(), "cancel"));
            }

            if (order.canBeCompleted()) {
                orderModel.add(snackShopLinks.linkToOrderDelivery(order.getCode(), "delivery"));
            }
        }

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            orderModel.getSnackShop().add(
                    snackShopLinks.linkToSnackShop(order.getSnackShop().getId()));
        }

        if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
            orderModel.getClient().add(
                    snackShopLinks.linkToUser(order.getClient().getId()));
        }

        if (snackShopSecurity.podeConsultarFormasPagamento()) {
            orderModel.getPaymentMethod().add(
                    snackShopLinks.linkToPaymentMethods(order.getPaymentMethod().getId()));
        }

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            orderModel.getItems().forEach(item -> {
                item.add(snackShopLinks.linkToProduct(
                        orderModel.getSnackShop().getId(), item.getProductId(), "product"));
            });
        }
        
        return orderModel;
    }

}
