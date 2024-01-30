package com.proinsight.api.assembler;

import com.proinsight.api.controller.ProductController;
import com.proinsight.api.model.ProductModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(Product product) {
        ProductModel productModel = createModelWithId(
                product.getId(), product, product.getSnackShop().getId());
        modelMapper.map(product, productModel);

        if (snackShopSecurity.podeConsultarLanchonetes()) {

            productModel.add(snackShopLinks.linkToProductImage(
                    product.getSnackShop().getId(), product.getId(), "image"));
        }

        return productModel;
    }
}
