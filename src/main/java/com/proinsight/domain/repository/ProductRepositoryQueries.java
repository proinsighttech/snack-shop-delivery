package com.proinsight.domain.repository;

import com.proinsight.domain.model.ProductImage;

public interface ProductRepositoryQueries {

    ProductImage save(ProductImage image);

    void delete(ProductImage image);

}

