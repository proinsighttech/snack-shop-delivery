package com.proinsight.api.facade.impl;

import com.proinsight.api.assembler.SnackShopInputDisassembler;
import com.proinsight.api.assembler.SnackShopModelAssembler;
import com.proinsight.api.facade.SnackShopFacade;
import com.proinsight.api.model.SnackShopModel;
import com.proinsight.api.model.input.SnackShopInput;
import com.proinsight.domain.model.SnackShop;
import com.proinsight.domain.repository.SnackShopRepository;
import com.proinsight.domain.service.SnackShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SnackShopFacadeImpl implements SnackShopFacade {

    @Autowired
    private SnackShopRepository snackShopRepository;

    @Autowired
    private SnackShopService snackShopService;

    @Autowired
    private SnackShopModelAssembler snackShopModelAssembler;

    @Autowired
    private SnackShopInputDisassembler snackShopInputDisassembler;

    public CollectionModel<SnackShopModel> list() {
        List<SnackShop> allSnackShops = snackShopRepository.findAll();
        return snackShopModelAssembler.toCollectionModel(allSnackShops);
    }

    public SnackShopModel find(Long snackShopId) {
        SnackShop snackShop = snackShopService.findOrThrow(snackShopId);
        return snackShopModelAssembler.toModel(snackShop);
    }

    public SnackShopModel addSnackShop(SnackShopInput snackShopInput) {
        SnackShop snackShop = snackShopInputDisassembler.toDomainObject(snackShopInput);
        return snackShopModelAssembler.toModel(snackShopService.save(snackShop));
    }

    public SnackShopModel updateSnackShop(Long snackShopId, SnackShopInput snackShopInput) {
        SnackShop currentSnackShop = snackShopService.findOrThrow(snackShopId);
        snackShopInputDisassembler.copyToDomainObject(snackShopInput, currentSnackShop);
        return snackShopModelAssembler.toModel(snackShopService.save(currentSnackShop));
    }

    public ResponseEntity<Void> activeSnackShop(Long snackShopId) {
        snackShopService.active(snackShopId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> inactiveSnackShop(Long snackShopId) {
        snackShopService.desactive(snackShopId);
        return ResponseEntity.noContent().build();
    }

}