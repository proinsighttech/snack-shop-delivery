package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.OrderInputDisassembler;
import com.proinsight.api.assembler.OrderModelAssembler;
import com.proinsight.api.assembler.OrderResumeModelAssembler;
import com.proinsight.api.core.data.PageWrapper;
import com.proinsight.api.core.data.PageableTranslator;
import com.proinsight.api.facade.OrderFacade;
import com.proinsight.api.model.OrderModel;
import com.proinsight.api.model.OrderResumeModel;
import com.proinsight.api.model.input.OrderInput;
import com.proinsight.domain.enums.OrderStatus;
import com.proinsight.domain.exception.BusinessException;
import com.proinsight.domain.exception.EntityNotFoundException;
import com.proinsight.domain.model.Order;
import com.proinsight.domain.model.SequentialCode;
import com.proinsight.domain.model.User;
import com.proinsight.domain.repository.OrderRepository;
import com.proinsight.domain.repository.filter.OrderFilter;
import com.proinsight.domain.repository.spec.OrderSpec;
import com.proinsight.domain.service.OrderFlowService;
import com.proinsight.domain.service.OrderService;
import com.proinsight.domain.service.SequentialCodeService;
import com.proinsight.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFlowService orderFlowService;

    @Autowired
    private OrderModelAssembler orderModelAssembler;

    @Autowired
    private OrderResumeModelAssembler orderResumeModelAssembler;

    @Autowired
    private OrderInputDisassembler orderInputDisassembler;

    @Autowired
    private UserService userService;

    @Autowired
    private SequentialCodeService sequentialCodeService;

    @Autowired
    private PagedResourcesAssembler<Order> pagedResourcesAssembler;

    public PagedModel<OrderResumeModel> findAll(OrderFilter filter, Pageable pageable, boolean filteredResults) {
        Pageable mappedPageable = mappingPageable(pageable);

        Page<Order> ordersPage = orderRepository.findAll(
                OrderSpec.usingFilter(filter), mappedPageable);

        if (filteredResults) {
            List<Order> filteredOrders = ordersPage.get()
                    .filter(o -> o.getStatus() == OrderStatus.READY || o.getStatus() == OrderStatus.IN_PROGRESS || o.getStatus() == OrderStatus.RECEIVED)
                    .sorted(Comparator.comparing(Order::getStatus, Comparator.comparing(OrderStatus::getOrder))
                            .thenComparing(Order::getReceptionDate))
                    .collect(Collectors.toList());
            ordersPage = new PageImpl<>(filteredOrders, pageable, ordersPage.getTotalElements());
        }
        else {
            ordersPage = new PageWrapper<>(ordersPage, pageable);
        }

        return pagedResourcesAssembler.toModel(ordersPage, orderResumeModelAssembler);
    }

    public OrderModel addOrder(OrderInput orderInput) {
        try {
            Order newOrder = orderInputDisassembler.toDomainObject(orderInput);
            User user = userService.findByCpfOrThrow(orderInput.getClient().getCpf());
            SequentialCode sequentialCode = sequentialCodeService.saveAndIncrement("ORDERS");

            newOrder.setClient(user);
            newOrder.getClient().setId(user.getId());
            newOrder.setCode(sequentialCode.getCode());
            newOrder = orderService.create(newOrder);
            orderFlowService.receive(newOrder.getId());

            return orderModelAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public OrderModel find(Long orderCode) {
        Order order = orderService.findOrThrow(orderCode);
        return orderModelAssembler.toModel(order);
    }

    private Pageable mappingPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "code", "code",
                "total", "total",
                "receivedDate", "receivedDate",
                "snackShop.name", "snackShop.name",
                "snackShop.id", "snackShop.id",
                "client.id", "client.id",
                "client.name", "client.name"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}