package com.proinsight.api.facade;

import com.proinsight.api.model.ProductImageModel;
import com.proinsight.api.model.input.ImageProductInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImageFacade {
    ProductImageModel updateImage(Long snackShopId, Long productId, ImageProductInput imageProductInput, MultipartFile file) throws IOException;

    void delete(Long snackShopId, Long productId);

    ProductImageModel findImage(Long snackShopId, Long productId);

    ResponseEntity<?> toServe( Long snackShopId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

}
