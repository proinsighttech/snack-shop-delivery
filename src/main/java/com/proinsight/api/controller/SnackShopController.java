package com.proinsight.api.controller;

import com.proinsight.api.facade.SnackShopFacade;
import com.proinsight.api.model.SnackShopModel;
import com.proinsight.api.model.input.SnackShopInput;
import com.proinsight.api.security.CheckSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/snackshops")
public class SnackShopController {

    @Autowired
    private SnackShopFacade snackShopFacade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<SnackShopModel> list() {
        return snackShopFacade.list();
    }

    @CheckSecurity.SnackShops.PodeConsultar
    @GetMapping(value = "/{snackShopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SnackShopModel find(@PathVariable Long snackShopId) {
        return snackShopFacade.find(snackShopId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SnackShopModel addSnackShop(@RequestBody @Valid SnackShopInput snackShopInput) {
        return snackShopFacade.addSnackShop(snackShopInput);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping(value = "/{snackShopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SnackShopModel updateSnackShop(@PathVariable Long snackShopId,
                                      @RequestBody @Valid SnackShopInput snackShopInput) {
        return snackShopFacade.updateSnackShop(snackShopId, snackShopInput);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{snackShopId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> activeSnackShop(@PathVariable Long snackShopId) {
        return snackShopFacade.activeSnackShop(snackShopId);
    }

    @CheckSecurity.SnackShops.PodeGerenciarCadastro
    @PutMapping("/{snackShopId}/desactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inactiveSnackShop(@PathVariable Long snackShopId) {
        return snackShopFacade.inactiveSnackShop(snackShopId);
    }

}

