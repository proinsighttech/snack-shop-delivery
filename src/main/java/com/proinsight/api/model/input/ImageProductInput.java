package com.proinsight.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageProductInput {

    @NotNull
    private MultipartFile file;

    @NotBlank
    private String description;

}

