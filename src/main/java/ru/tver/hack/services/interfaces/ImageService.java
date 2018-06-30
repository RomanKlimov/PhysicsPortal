package ru.tver.hack.services.interfaces;


import ru.tver.hack.models.ImageInfo;

import javax.servlet.http.HttpServletResponse;


public interface ImageService {
    void saveImage(ImageInfo imageInfo);
    ImageInfo getImageByUID(Long uid);

    void getPicture(String name, HttpServletResponse response);
}
