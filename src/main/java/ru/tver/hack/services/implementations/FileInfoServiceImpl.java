package ru.tver.hack.services.implementations;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tver.hack.models.FileInfo;
import ru.tver.hack.repositories.FileInfoRepository;
import ru.tver.hack.services.interfaces.FileInfoService;
import ru.tver.hack.utils.FileStorageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public void getPicture(String fileName, HttpServletResponse response) {
        Optional<FileInfo> fileInfoOptional = fileInfoRepository.findFirstByFileName(fileName);
        if (fileInfoOptional.isPresent()){
            FileInfo fileInfo = fileInfoOptional.get();
            response.setContentType(fileInfo.getType());

            try {
                InputStream inputStream = new FileInputStream(new File(fileInfo.getUrl()));
                IOUtils.copy(inputStream, response.getOutputStream());
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public FileInfo savePicture(MultipartFile multipartFile) {
        FileInfo fileInfo = fileStorageUtil.convertFromMutipart(multipartFile);
        fileInfoRepository.save(fileInfo);
        fileStorageUtil.copyToStorage(multipartFile, fileInfo.getFileName());
        return fileInfo;
    }
}
