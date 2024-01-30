package com.proinsight.api.assembler;

import com.proinsight.api.controller.ProductImageController;
import com.proinsight.api.model.ProductImageModel;
import com.proinsight.api.security.SnackShopLinks;
import com.proinsight.api.security.SnackShopSecurity;
import com.proinsight.domain.model.ProductImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductImageModelAssembler
        extends RepresentationModelAssemblerSupport<ProductImage, ProductImageModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SnackShopLinks snackShopLinks;

    @Autowired
    private SnackShopSecurity snackShopSecurity;

    public ProductImageModelAssembler() {
        super(ProductImageController.class, ProductImageModel.class);
    }

        @Override
        public ProductImageModel toModel(ProductImage image) {
            ProductImageModel productImageModel = modelMapper.map(image, ProductImageModel.class);

            if (snackShopSecurity.podeConsultarLanchonetes()) {
                productImageModel.add(snackShopLinks.linkToProductImage(
                        image.getSnackShopId(), image.getProduct().getId()));

                productImageModel.add(snackShopLinks.linkToProduct(
                        image.getSnackShopId(), image.getProduct().getId(), "product"));
            }

        return productImageModel;
    }
}

