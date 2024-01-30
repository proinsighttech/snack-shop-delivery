package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class PasswordInput {
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
