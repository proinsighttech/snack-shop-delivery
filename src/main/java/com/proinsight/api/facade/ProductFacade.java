package com.proinsight.api.facade;

import com.proinsight.api.model.ProductModel;
import com.proinsight.api.model.input.ProductInput;
import com.proinsight.domain.repository.filter.ProductFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface ProductFacade {

    PagedModel<ProductModel> listAllProducts(ProductFilter filter, Pageable pageable);

    ProductModel findProduct(Long snackShopId, Long productId);

    ProductModel addProduct(Long snackShopId, ProductInput productInput);

    ProductModel updateProduct(Long snackShopId, Long productId, ProductInput productInput);
}
