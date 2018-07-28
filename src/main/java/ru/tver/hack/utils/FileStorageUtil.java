package ru.tver.hack.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.tver.hack.models.FileInfo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Component
public class FileStorageUtil {

    @Value("${storage.path}")
    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }

    public void copyToStorage(MultipartFile file, String fileName){
        try {
            Files.copy(file.getInputStream(), Paths.get(storagePath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInfo convertFromMutipart(MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String type = file.getContentType();

        String storageName = createStorageName(originalFilename);
        String urlOfFile = getUrlOfFile(storageName);
        return FileInfo.builder()
                        .fileName(storageName)
                        .type(type)
                        .url(urlOfFile)
                        .build();
    }

    private String getUrlOfFile(String storageFileName) {
        return storagePath + "/" + storageFileName;
    }

    private String createStorageName(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        String newFileName = UUID.randomUUID().toString();
        return newFileName + "." + extension;
    }
}
