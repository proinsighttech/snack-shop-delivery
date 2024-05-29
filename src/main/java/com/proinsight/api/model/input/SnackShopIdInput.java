package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SnackShopIdInput {

    @NotNull
    private Long id;
}
