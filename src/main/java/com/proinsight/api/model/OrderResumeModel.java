package com.proinsight.api.model;

import com.proinsight.domain.enums.OrderStatus;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Relation(collectionRelation = "orders")
public class OrderResumeModel extends RepresentationModel<OrderResumeModel> {

    private String code;

    private BigDecimal total;

    private String status;

    private OffsetDateTime receptionDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime preparationDate;

    private OffsetDateTime finalizationDate;

    private SnackShopOnlyNameModel snackShop;

    private UserModel client;

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status).getDescription();
    }
}

