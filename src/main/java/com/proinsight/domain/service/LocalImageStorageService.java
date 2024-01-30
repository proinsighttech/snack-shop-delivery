package com.proinsight.domain.service;

import com.proinsight.domain.exception.StorageException;
import com.proinsight.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalImageStorageService implements ImageStorageService{

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void store(NewImage newImage) {
        Path filePath = getFilePath(newImage.getFileName());

        try {
            FileCopyUtils.copy(newImage.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }

    }

    @Override
    public void remove(String fileName) {
        Path arquivoPath = getFilePath(fileName);
        try {
            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo.", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal().getImagePath()
                .resolve(Path.of(fileName));
    }

    @Override
    public ImageRecovered recover(String fileName){
        try {
            Path filePath = getFilePath(fileName);

            return ImageRecovered.builder()
                    .inputStream(Files.newInputStream(filePath)).build();
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }



}
