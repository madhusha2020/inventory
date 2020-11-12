package com.watersolution.inventory.component.management.image.service;

import com.watersolution.inventory.component.management.image.model.ImageModel;

public interface ImageUploadService {
    ImageModel convertImageToBase64(ImageModel imageModel);
    ImageModel uploadImage(ImageModel imageModel, String category, String id);
    ImageModel getImage(String category, String id);
}
