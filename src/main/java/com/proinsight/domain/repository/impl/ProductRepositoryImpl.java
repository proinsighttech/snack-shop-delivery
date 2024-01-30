package com.proinsight.domain.repository.impl;

import com.proinsight.domain.model.ProductImage;
import com.proinsight.domain.repository.ProductRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;



@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public ProductImage save(ProductImage image) {
        return manager.merge(image);
    }

    @Override
    @Transactional
    public void delete(ProductImage image) {
        manager.remove(image);
    }
}

