package com.proinsight.api.controller;

import com.proinsight.api.facade.IngredientFacade;
import com.proinsight.api.model.IngredientModel;
import com.proinsight.api.model.IngredientStockModel;
import com.proinsight.api.model.input.IngredientInput;
import com.proinsight.api.security.CheckSecurity;
import com.proinsight.domain.repository.filter.IngredientFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ingredients")
public class IngredientController {

    @Autowired
    private IngredientFacade ingredientFacade;

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<IngredientStockModel> findAll(
            IngredientFilter filter, @PageableDefault(size = 10) Pageable pageable) {
        return ingredientFacade.findAll(filter, pageable);
    }

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping("/{ingredientId}")
    public IngredientStockModel findIngredient(@PathVariable Long ingredientId) {
            return ingredientFacade.findIngredient(ingredientId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientModel addIngredient(@RequestBody @Valid IngredientInput ingredientInput) {
        return ingredientFacade.addIngredient(ingredientInput);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{ingredientId}")
    public IngredientModel updateIngredient(@PathVariable Long ingredientId,
                                      @RequestBody @Valid IngredientInput ingredientInput) {
        return ingredientFacade.updateIngredient(ingredientId, ingredientInput);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @DeleteMapping("/{ingredientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable Long ingredientId) {
        ingredientFacade.deleteIngredient(ingredientId);
    }

}
