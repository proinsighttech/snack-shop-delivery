package com.proinsight.domain.service;

import com.proinsight.domain.exception.ProductNotFoundException;
import com.proinsight.domain.model.Product;
import com.proinsight.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findOrThrow(Long snackShopId, Long productId) {
        return productRepository.findById(snackShopId, productId)
                .orElseThrow(() -> new ProductNotFoundException(snackShopId, productId));
    }

    public Product findOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
