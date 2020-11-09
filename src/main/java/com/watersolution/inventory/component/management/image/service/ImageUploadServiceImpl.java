package com.watersolution.inventory.component.management.image.service;

import com.watersolution.inventory.component.management.image.factory.ImageFactory;
import com.watersolution.inventory.component.management.image.model.ImageModel;
import com.watersolution.inventory.component.management.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private ImageFactory imageFactory;

    @Override
    public ImageModel uploadImage(ImageModel imageModel, String category, long id) {
        imageModel.setPhoto(imageUtil.compressBytes(imageModel.getPhoto()));
        imageFactory.uploadImage(imageModel, category, id);
        imageModel.setPhoto(imageUtil.decompressBytes(imageModel.getPhoto()));
        return imageModel;
    }

    @Override
    public ImageModel getImage(String category, long id) {
        return imageFactory.getImage(category, id);
    }
}
