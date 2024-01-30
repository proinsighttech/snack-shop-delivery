package com.proinsight.domain.repository;

import com.proinsight.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    Optional<Order> findByCode(Long code);

    @Query("from Order o join fetch o.client join fetch o.snackShop s")
    List<Order> findAll();

    @Query("from Order o join fetch o.client join fetch o.snackShop s where s.id = :snackShopId")
    Optional<List<Order>> findAllBySnackShop(Long snackShopId);

    @Query("select case when count(1) > 0 then true else false end " +
            "from Order o join o.snackShop s join s.admins a where o.code = :orderCode and o.client.id = :userId")
    boolean isOrderManagedBy(Long orderCode, Long userId);

}

