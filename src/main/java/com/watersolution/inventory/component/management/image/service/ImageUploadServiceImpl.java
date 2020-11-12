package com.watersolution.inventory.component.management.image.service;

import com.watersolution.inventory.component.common.validator.CustomValidator;
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
    @Autowired
    private CustomValidator customValidator;


    @Override
    public ImageModel convertImageToBase64(ImageModel imageModel) {
        imageModel.setPhoto(imageUtil.compressBytes(imageModel.getPhoto()));
        return imageModel;
    }

    @Override
    public ImageModel uploadImage(ImageModel imageModel, String category, String id) {
        customValidator.validateNullOrEmpty(category, "category");
        customValidator.validateNullOrEmpty(id, "id");
        imageModel.setPhoto(imageUtil.compressBytes(imageModel.getPhoto()));
        imageFactory.uploadImage(imageModel, category, Long.valueOf(id));
        imageModel.setPhoto(imageUtil.decompressBytes(imageModel.getPhoto()));
        return imageModel;
    }

    @Override
    public ImageModel getImage(String category, String id) {
        customValidator.validateNullOrEmpty(id, "id");
        return imageFactory.getImage(category, Long.valueOf(id));
    }
}
