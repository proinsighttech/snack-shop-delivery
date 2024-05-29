package com.proinsight.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface ImageStorageService {

    ImageRecovered recover(String fileName);

    void store(NewImage newImage);

    void remove(String fileName);

    default void replace(String oldFileName, NewImage newImage) {
        this.store(newImage);

        if(oldFileName != null) {
            this.remove(oldFileName);
        }
    }

    default String generateFileName(String originalName) {
        return UUID.randomUUID() + "_" + originalName;
    }

    @Getter
    @Builder
    class NewImage {

        private String fileName;
        private String contentType;
        private InputStream inputStream;
    }

    @Getter
    @Builder
    class ImageRecovered{

        private InputStream inputStream;
        private String url;

        public boolean hasUrl() {
            return url != null;
        }

        public boolean hasInputStream() {
            return inputStream != null;
        }
    }
}

