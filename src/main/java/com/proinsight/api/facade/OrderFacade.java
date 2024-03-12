package com.proinsight.api.facade;

import com.proinsight.api.model.OrderModel;
import com.proinsight.api.model.OrderResumeModel;
import com.proinsight.api.model.input.OrderInput;
import com.proinsight.domain.repository.filter.OrderFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface OrderFacade {

    PagedModel<OrderResumeModel> findAll(OrderFilter filter, Pageable pageable, boolean filteredResults);

    OrderModel addOrder(OrderInput orderInput) ;

    OrderModel find(Long orderCode);

}
