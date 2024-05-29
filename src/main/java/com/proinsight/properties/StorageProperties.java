package com.proinsight.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Data
@Component
@ConfigurationProperties("snackshop.storage")
public class StorageProperties {

    private Local local = new Local();
    private StorageType type = StorageType.LOCAL;
    public enum StorageType {
        LOCAL
    }

    @Data
    public class Local {
        private Path imagePath;
    }
}