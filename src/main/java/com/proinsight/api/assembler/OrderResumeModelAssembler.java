package com.proinsight.api.assembler;

import com.proinsight.api.controller.OrderController;
import com.proinsight.api.model.OrderResumeModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderResumeModelAssembler
        extends RepresentationModelAssemblerSupport<Order, OrderResumeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public OrderResumeModelAssembler() {
        super(OrderController.class, OrderResumeModel.class);
    }

    @Override
    public OrderResumeModel toModel(Order order) {
        OrderResumeModel orderResumeModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderResumeModel);

        if (snackShopSecurity.podePesquisarPedidos()) {
            orderResumeModel.add(snackShopLinks.linkToOrders("orders"));
        }

        if (snackShopSecurity.podeConsultarLanchonetes()) {
            orderResumeModel.getSnackShop().add(
                    snackShopLinks.linkToSnackShop(order.getSnackShop().getId()));
        }

        if (snackShopSecurity.podeConsultarUsuariosGruposPermissoes()) {
            orderResumeModel.getClient().add(snackShopLinks.linkToUser(order.getClient().getId()));
        }

        return orderResumeModel;
    }

}

