package com.proinsight.api.controller;

import com.proinsight.api.facade.ProductIngredientFacade;
import com.proinsight.api.model.IngredientModel;
import com.proinsight.api.security.CheckSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products/{productId}/ingredients")
public class ProductIngredientController {

    @Autowired
    private ProductIngredientFacade productIngredientFacade;

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<IngredientModel> listAllIngredients(@PathVariable Long productId) {
        return productIngredientFacade.listAllIngredients(productId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{ingredientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addIngredientToProduct(@PathVariable Long productId, @PathVariable Long ingredientId) {
        return productIngredientFacade.addIngredientToProduct(productId, ingredientId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @DeleteMapping("/{ingredientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeIngredientToProduct(@PathVariable Long productId, @PathVariable Long ingredientId) {
        return productIngredientFacade.removeIngredientToProduct(productId, ingredientId);
    }

}