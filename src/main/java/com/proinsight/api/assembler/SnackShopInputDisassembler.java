package com.proinsight.api.assembler;

import com.proinsight.api.model.input.SnackShopInput;
import com.proinsight.domain.model.SnackShop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SnackShopInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public SnackShop toDomainObject(SnackShopInput snackShopInput) {
        return modelMapper.map(snackShopInput, SnackShop.class);
    }

    public void copyToDomainObject(SnackShopInput snackShopInput, SnackShop snackShop) {
        modelMapper.map(snackShopInput, snackShop);
    }

}

