package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.ProductInputDisassembler;
import com.proinsight.api.assembler.ProductModelAssembler;
import com.proinsight.api.core.data.PageWrapper;
import com.proinsight.api.core.data.PageableTranslator;
import com.proinsight.api.facade.ProductFacade;
import com.proinsight.api.model.ProductModel;
import com.proinsight.api.model.input.ProductInput;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.repository.ProductRepository;
import com.proinsight.domain.repository.filter.ProductFilter;
import com.proinsight.domain.repository.spec.ProductSpec;
import com.proinsight.domain.service.ProductService;
import com.proinsight.domain.service.SnackShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductFacadeImpl implements ProductFacade {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SnackShopService snackShopService;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @Autowired
    private ProductInputDisassembler productInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Product> pagedResourcesAssembler;

    public PagedModel<ProductModel> listAllProducts(ProductFilter filter, Pageable pageable) {
        Pageable mappedPageable = mappingPageable(pageable);

        Page<Product> productsPage = productRepository.findAll(
                ProductSpec.usingFilter(filter), mappedPageable);

        productsPage = new PageWrapper<>(productsPage, pageable);

        return pagedResourcesAssembler.toModel(productsPage, productModelAssembler);
    }

    public ProductModel findProduct(Long snackShopId, Long productId) {
        Product product = productService.findOrThrow(snackShopId, productId);
        return productModelAssembler.toModel(product);
    }

    public ProductModel addProduct(Long snackShopId, ProductInput productInput) {
        SnackShop snackShop = snackShopService.findOrThrow(snackShopId);
        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setSnackShop(snackShop);
        product = productService.save(product);
        return productModelAssembler.toModel(product);
    }

    public ProductModel updateProduct(Long snackShopId, Long productId, ProductInput productInput) {
        Product currentProduct = productService.findOrThrow(snackShopId, productId);
        productInputDisassembler.copyToDomainObject(productInput, currentProduct);
        currentProduct = productService.save(currentProduct);
        return productModelAssembler.toModel(currentProduct);
    }

    private Pageable mappingPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "id", "id",
                "name", "name",
                "description", "description",
                "price", "price",
                "category", "category",
                "active", "active",
                "ingredients.name", "ingredients.name"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}