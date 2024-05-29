package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SnackShopInput {

    @NotBlank
    private String name;
}
