package ru.tver.hack.services.implementations;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tver.hack.models.ImageInfo;
import ru.tver.hack.repositories.ImageRepository;
import ru.tver.hack.services.interfaces.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Date 16.04.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void saveImage(ImageInfo imageInfo) {
        imageRepository.save(imageInfo);
    }

    @Override
    public ImageInfo getImageByUID(Long uid) {
        return imageRepository.findFirstByUid(uid);
    }

    @Override
    public void getPicture(String name, HttpServletResponse response) {
        Optional<ImageInfo> imageInfoOptional = imageRepository.findFirstByStorageFileName(name);
        if (imageInfoOptional.isPresent()){
            ImageInfo imageInfo = imageInfoOptional.get();
            File file = new File(imageInfo.getUrl());
            try {
                InputStream is = new FileInputStream(file);
                response.setContentType(imageInfo.getType());
                IOUtils.copy(is,response.getOutputStream() );
                response.flushBuffer();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
