package com.watersolution.inventory.component.management.delivery.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.delivery.model.api.DeliveryList;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.delivery.service.DeliveryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of deliveries", response = DeliveryList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<DeliveryList> getAllDeliveries() {
        return ResponseCreator.successfulResponse(deliveryService.getAllDeliveries());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get delivery by id", response = Delivery.class)
    @CrossOrigin
    @GetMapping(value = "/{deliveryId}", produces = "application/json")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable("deliveryId") String deliveryId) {
        return ResponseCreator.successfulResponse(deliveryService.getDeliveryById(deliveryId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update delivery details", response = Delivery.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<Delivery> updateDelivery(@RequestBody Delivery delivery) {
        return ResponseCreator.successfulResponse(deliveryService.updateDelivery(delivery));
    }
}
