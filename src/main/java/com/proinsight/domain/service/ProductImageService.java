package com.proinsight.domain.service;

import com.proinsight.domain.exception.ProductImageNotFoundException;
import com.proinsight.domain.model.ProductImage;
import com.proinsight.domain.repository.ProductRepository;
import com.proinsight.domain.service.ImageStorageService.NewImage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;


@Service
public class ProductImageService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    @Transactional
    public ProductImage save(ProductImage image, InputStream fileData) {
        Long snackShopId = image.getSnackShopId();
        Long productId = image.getProduct().getId();

        String newFileName =  imageStorageService.generateFileName(image.getFileName());
        String existsFileName = null;

        Optional<ProductImage> existsImage = productRepository
                .findImageById(snackShopId, productId);

        if(existsImage.isPresent()) {
            existsFileName = existsImage.get().getFileName();
            productRepository.delete(existsImage.get());
        }

        image.setFileName(newFileName);
        image = productRepository.save(image);
        productRepository.flush();

        NewImage newImage = NewImage.builder()
                .fileName(image.getFileName())
                .contentType(image.getContentType())
                .inputStream(fileData)
                .build();

        imageStorageService.replace(existsFileName, newImage);

        return image;
    }

    public ProductImage findOrThrow(Long snackShopId, Long productId) {
        return productRepository.findImageById(snackShopId, productId)
                .orElseThrow(() -> new ProductImageNotFoundException(snackShopId, productId));
    }

    @Transactional
    public void delete(Long snackShopId, Long productId) {
        ProductImage image = findOrThrow(snackShopId, productId);

        productRepository.delete(image);
        productRepository.flush();

        imageStorageService.remove(image.getFileName());
    }
}