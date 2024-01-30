package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPassInput extends UserInput{

    @NotBlank
    private String password;

}

