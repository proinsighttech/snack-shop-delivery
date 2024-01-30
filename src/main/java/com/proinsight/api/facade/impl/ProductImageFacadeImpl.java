package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.ProductImageModelAssembler;
import com.proinsight.api.facade.ProductImageFacade;
import com.proinsight.api.model.ProductImageModel;
import com.proinsight.api.model.input.ImageProductInput;
import com.proinsight.domain.exception.EntityNotFoundException;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.model.ProductImage;
import com.proinsight.domain.service.ImageStorageService;
import com.proinsight.domain.service.ProductImageService;
import com.proinsight.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ProductImageFacadeImpl implements ProductImageFacade {



    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private ProductImageModelAssembler productImageModelAssembler;

    public ProductImageModel updateImage(Long snackShopId, Long productId, ImageProductInput imageProductInput, MultipartFile file) throws IOException {
        Product product = productService.findOrThrow(snackShopId, productId);

        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setDescription(imageProductInput.getDescription());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setFileName(file.getOriginalFilename());
        ProductImage savedImage = productImageService.save(image, file.getInputStream());
        return productImageModelAssembler.toModel(savedImage);
    }

    public void delete(Long snackShopId, Long productId) {
        productImageService.delete(snackShopId, productId);
    }

    public ProductImageModel findImage(Long snackShopId, Long productId) {
        ProductImage productImage = productImageService.findOrThrow(snackShopId, productId);
        return productImageModelAssembler.toModel(productImage);
    }

    public ResponseEntity<?> toServe(Long snackShopId, Long productId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            ProductImage productImage = productImageService.findOrThrow(snackShopId, productId);

            MediaType mediaTypeImage = MediaType.parseMediaType(productImage.getContentType());
            List<MediaType> mediaTypesAccpted = MediaType.parseMediaTypes(acceptHeader);
            checkCompatibilityMediaType(mediaTypeImage, mediaTypesAccpted);

            ImageStorageService.ImageRecovered imageRecovered = imageStorageService.recover(productImage.getFileName());

            if (imageRecovered.hasUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, imageRecovered.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeImage)
                        .body(new InputStreamResource(imageRecovered.getInputStream()));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void checkCompatibilityMediaType(MediaType mediaTypeFoto,
                                             List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

}