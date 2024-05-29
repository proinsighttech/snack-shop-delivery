package com.proinsight.api.core.storage;

import com.proinsight.domain.service.ImageStorageService;
import com.proinsight.domain.service.LocalImageStorageService;
import com.proinsight.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public ImageStorageService fotoStorageService() {
        return new LocalImageStorageService();
    }

}