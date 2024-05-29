package com.proinsight.api.controller;

import com.proinsight.api.facade.OrderFacade;
import com.proinsight.api.model.OrderModel;
import com.proinsight.api.model.OrderResumeModel;
import com.proinsight.api.model.input.OrderInput;
import com.proinsight.api.security.CheckSecurity;
import com.proinsight.domain.repository.filter.OrderFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @CheckSecurity.Orders.PodeBuscar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<OrderResumeModel> findAll(OrderFilter filter,
                                                @PageableDefault(size = 10) Pageable pageable,
                                                @Param("orderedResults") boolean filteredResults){
        return orderFacade.findAll(filter, pageable, filteredResults);
    }

    @CheckSecurity.Orders.PodeCriar
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel addOrder(@Valid @RequestBody OrderInput orderInput) {
        return orderFacade.addOrder(orderInput);
    }

    @CheckSecurity.Orders.PodeBuscar
    @GetMapping(value = "/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel find(@PathVariable Long orderCode) {
        return orderFacade.find(orderCode);
    }

}

