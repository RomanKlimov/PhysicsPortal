package ru.tver.hack.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.tver.hack.models.ImageInfo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Component
public class ImageStorageUtil {

    @Value("${storage.path}")
    private String storagePath;

    public String getStoragePath(){
        return storagePath;
    }

    @SneakyThrows
    private void copyToStorage(MultipartFile file, String fileName){
        Files.copy(file.getInputStream(), Paths.get(getStoragePath(),fileName));
    }

    public ImageInfo convertFromMultipartFileAndSave(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String type = file.getContentType();
        String storageName = createStorageName(originalFilename);
        String urlOfFile = getUrlOfFile(storageName);
        copyToStorage(file, storageName);
        return ImageInfo.builder()
                .type(type)
                .storageFileName(storageName)
                .url(urlOfFile)
                .build();
    }

    private String createStorageName(String originalFileName){
        String extension = FilenameUtils.getExtension(originalFileName);
        String fileName = UUID.randomUUID().toString();
        return fileName+"."+extension;
    }

    private String getUrlOfFile(String storageFileName){
        return getStoragePath()+"/"+storageFileName;
    }
}
