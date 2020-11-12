package com.watersolution.inventory.component.management.image.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.image.model.ImageModel;
import com.watersolution.inventory.component.management.image.service.ImageUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "image")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @SneakyThrows
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @ApiOperation(value = "Upload Image", response = ImageModel.class)
    @PostMapping(value = "/convert", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<ImageModel> convertImageToBase64(@RequestParam("imageFile") MultipartFile file) {
        return ResponseCreator.successfulResponse(imageUploadService.convertImageToBase64(new ImageModel(file.getBytes())));
    }

    @SneakyThrows
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @ApiOperation(value = "Upload Image", response = ImageModel.class)
    @PostMapping(value = "/upload", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<ImageModel> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("category") String category, @RequestParam("id") String id) {
        return ResponseCreator.successfulResponse(imageUploadService.uploadImage(new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes()), category, id));
    }

    @SneakyThrows
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @ApiOperation(value = "Get Image", response = ImageModel.class)
    @CrossOrigin
    @GetMapping(path = {"/get/{category}/{id}"}, produces = "application/json")
    public ResponseEntity<ImageModel> getImage(@PathVariable("category") String category, @PathVariable("id") String id) {
        return ResponseCreator.successfulResponse(imageUploadService.getImage(category, id));
    }
}
