package ru.tver.hack.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.tver.hack.models.FileInfo;

import javax.servlet.http.HttpServletResponse;

public interface FileInfoService {
    void getPicture(String fileName, HttpServletResponse response);
    FileInfo savePicture(MultipartFile multipartFile);
}
