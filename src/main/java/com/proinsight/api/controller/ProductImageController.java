package com.proinsight.api.controller;

import com.proinsight.api.facade.ProductImageFacade;
import com.proinsight.api.model.ProductImageModel;
import com.proinsight.api.model.input.ImageProductInput;
import com.proinsight.api.security.CheckSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/snackshops/{snackShopId}/products/{productId}/image")
public class ProductImageController {

    @Autowired
    private ProductImageFacade productImageFacade;

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductImageModel updateImage(@PathVariable Long snackShopId,
                                         @PathVariable Long productId, @Valid ImageProductInput imageProductInput,
                                         @RequestPart(required = true) MultipartFile file) throws IOException {
        return productImageFacade.updateImage(snackShopId, productId, imageProductInput, file);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long snackShopId,
                        @PathVariable Long productId) {
        productImageFacade.delete(snackShopId, productId);
    }

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductImageModel findImage(@PathVariable Long snackShopId,
                                   @PathVariable Long productId) {
        return productImageFacade.findImage(snackShopId, productId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> toServe(@PathVariable Long snackShopId,
                                    @PathVariable Long productId, @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        return productImageFacade.toServe(snackShopId, productId, acceptHeader);
    }

}
