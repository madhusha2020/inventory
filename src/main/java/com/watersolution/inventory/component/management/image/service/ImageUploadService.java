package com.watersolution.inventory.component.management.image.service;

import com.watersolution.inventory.component.management.image.model.ImageModel;

public interface ImageUploadService {
    ImageModel uploadImage(ImageModel imageModel, String category, long id);
    ImageModel getImage(String category, long id);
}
