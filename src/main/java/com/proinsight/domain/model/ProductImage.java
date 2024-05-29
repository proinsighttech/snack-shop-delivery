package com.proinsight.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@Table(name = "product_image")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductImage {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;


    public Long getSnackShopId() {
        if(getProduct() != null) {
            return getProduct().getSnackShop().getId();
        }
        return null;
    }
}
