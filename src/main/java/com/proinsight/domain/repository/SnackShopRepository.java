package com.proinsight.domain.repository;

import com.proinsight.domain.model.SnackShop;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SnackShopRepository extends CustomJpaRepository<SnackShop, Long>,
        SnackShopRepositoryQueries, JpaSpecificationExecutor<SnackShop> {

    @Query("from SnackShop r")
    List<SnackShop> findAll();

    @Query("from SnackShop where name like %:name%")
    List<SnackShop> findByName (String name);

    @Query("from SnackShop s join s.admins a where s.id = :snackShopId and a.id = :userId")
    boolean existsAdmin(Long snackShopId, Long userId);
}
