package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.IngredientInputDisassembler;
import com.proinsight.api.assembler.IngredientModelAssembler;
import com.proinsight.api.assembler.IngredientStockModelAssembler;
import com.proinsight.api.core.data.PageWrapper;
import com.proinsight.api.core.data.PageableTranslator;
import com.proinsight.api.facade.IngredientFacade;
import com.proinsight.api.model.IngredientModel;
import com.proinsight.api.model.IngredientStockModel;
import com.proinsight.api.model.input.IngredientInput;
import com.proinsight.domain.model.Ingredient;
import com.proinsight.domain.model.IngredientStock;
import com.proinsight.domain.repository.IngredientStockRepository;
import com.proinsight.domain.repository.filter.IngredientFilter;
import com.proinsight.domain.repository.spec.IngredientStockSpec;
import com.proinsight.domain.service.IngredientService;
import com.proinsight.domain.service.IngredientStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IngredientFacadeImpl implements IngredientFacade {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientStockService ingredientStockService;

    @Autowired
    private IngredientStockRepository ingredientStockRepository;

    @Autowired
    private IngredientModelAssembler ingredientModelAssembler;

    @Autowired
    private IngredientInputDisassembler ingredientInputDisassembler;

    @Autowired
    private IngredientStockModelAssembler ingredientStockModelAssembler;

    @Autowired
    private PagedResourcesAssembler<IngredientStock> pagedResourcesAssembler;

    public PagedModel<IngredientStockModel> findAll(IngredientFilter filter, Pageable pageable) {
        Pageable mappedPageable = mappingPageable(pageable);
        Page<IngredientStock> ingredientsPage = ingredientStockRepository.findAll(
                IngredientStockSpec.usingFilter(filter), mappedPageable);
        ingredientsPage = new PageWrapper<>(ingredientsPage, pageable);
        return pagedResourcesAssembler.toModel(ingredientsPage, ingredientStockModelAssembler);
    }

    public IngredientStockModel findIngredient(Long ingredientId) {
        IngredientStock ingredientStock = ingredientStockService.findOrThrow(ingredientId);
        return ingredientStockModelAssembler.toModel(ingredientStock);
    }

    public IngredientModel addIngredient(IngredientInput ingredientInput) {
        Ingredient ingredient = ingredientInputDisassembler.toDomainObject(ingredientInput);
        ingredient = ingredientService.save(ingredient);
        return ingredientModelAssembler.toModel(ingredient);
    }

    public IngredientModel updateIngredient(Long ingredientId, IngredientInput ingredientInput) {
        Ingredient currentIngredient = ingredientService.findOrThrow(ingredientId);
        ingredientInputDisassembler.copyToDomainObject(ingredientInput, currentIngredient);
        currentIngredient = ingredientService.save(currentIngredient);
        return ingredientModelAssembler.toModel(currentIngredient);
    }

    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientService.findOrThrow(ingredientId);
        ingredientService.delete(ingredient);
    }

    private Pageable mappingPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "ingredientName", "ingredientName",
                "ingredientDescription", "ingredientDescription",
                "stockQuantity", "stockQuantity"
        );
        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}