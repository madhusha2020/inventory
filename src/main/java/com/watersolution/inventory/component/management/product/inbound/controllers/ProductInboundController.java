package com.watersolution.inventory.component.management.product.inbound.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.product.inbound.model.api.ProductInboundList;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;
import com.watersolution.inventory.component.management.product.inbound.service.ProductInboundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-inbound")
public class ProductInboundController {

    @Autowired
    private ProductInboundService productInboundService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all inbound products", response = ProductInboundList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<ProductInboundList> getAllProductInbounds() {
        return ResponseCreator.successfulResponse(productInboundService.getAllProductInbounds());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get inbound product by id", response = ProductInbound.class)
    @CrossOrigin
    @GetMapping(value = "/{productInboundId}", produces = "application/json")
    public ResponseEntity<ProductInbound> getProductInboundById(@PathVariable("productInboundId") String productInboundId) {
        return ResponseCreator.successfulResponse(productInboundService.getProductInboundById(productInboundId));
    }
}
