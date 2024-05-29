package com.proinsight.api.model;

import com.proinsight.domain.enums.OrderStatus;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Relation(collectionRelation = "orders")
public class OrderModel extends RepresentationModel<OrderModel> {

    private Long code;

    private BigDecimal total;

    private String status;

    private OffsetDateTime receptionDate;

    private OffsetDateTime cancelDate;

    private OffsetDateTime preparationDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime finalizationDate;

    private SnackShopOnlyNameModel snackShop;
    private UserModel client;
    private PaymentMethodModel paymentMethod;
    private List<OrderItemModel> items;

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status).getDescription();
    }

}
