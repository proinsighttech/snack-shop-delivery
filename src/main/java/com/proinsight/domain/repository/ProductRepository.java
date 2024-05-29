package com.proinsight.domain.repository;

import com.proinsight.domain.model.Product;
import com.proinsight.domain.model.ProductImage;
import com.proinsight.domain.model.SnackShop;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CustomJpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product>, ProductRepositoryQueries{

    @Query("from Product where snackShop.id = :snackShop and id = :product")
    Optional<Product> findById(@Param("snackShop") Long snackShopId,
                               @Param("product") Long productId);

    List<Product> findAllBySnackShop(SnackShop snackShop);

    @Query("from Product p where p.active = true and p.snackShop = :snackShop")
    List<Product> findActivesBySnackShop(SnackShop snackShop);

    @Query("select f from ProductImage f join f.product p "
            + "where p.snackShop.id = :snackShopId and f.product.id = :productId")
    Optional<ProductImage> findImageById(Long snackShopId, Long productId);

    @Query("select f from ProductImage f join f.product p "
            + "where p.snackShop.id = :snackShopId and f.product.id in :productIds")
    List<Product> findAll();
}
