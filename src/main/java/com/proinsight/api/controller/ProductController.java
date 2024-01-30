package com.proinsight.api.controller;

import com.proinsight.api.facade.ProductFacade;
import com.proinsight.api.model.ProductModel;
import com.proinsight.api.model.input.ProductInput;
import com.proinsight.api.security.CheckSecurity;
import com.proinsight.domain.repository.filter.ProductFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/snackshops/{snackShopId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<ProductModel> listAllProducts(ProductFilter filter,
                                                 @PageableDefault(size = 10) Pageable pageable) {
        return productFacade.listAllProducts(filter, pageable);
    }

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping("/{productId}")
    public ProductModel findProduct(@PathVariable Long snackShopId, @PathVariable Long productId) {
        return productFacade.findProduct(snackShopId, productId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel addProduct(@PathVariable Long snackShopId,
                                  @RequestBody @Valid ProductInput productInput) {
        return productFacade.addProduct(snackShopId, productInput);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{productId}")
    public ProductModel updateProduct(@PathVariable Long snackShopId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductInput productInput) {
        return productFacade.updateProduct(snackShopId, productId, productInput);
    }

}
