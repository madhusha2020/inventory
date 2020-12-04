package com.watersolution.inventory.component.management.product.outbound.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.product.outbound.model.api.ProductOutboundList;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutbound;
import com.watersolution.inventory.component.management.product.outbound.service.ProductOutboundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-outbound")
public class ProductOutboundController {

    @Autowired
    private ProductOutboundService productOutboundService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all outbound products", response = ProductOutboundList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<ProductOutboundList> getAllProductOutbounds() {
        return ResponseCreator.successfulResponse(productOutboundService.getAllProductOutbounds());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get outbound product by id", response = ProductOutbound.class)
    @CrossOrigin
    @GetMapping(value = "/{productOutboundId}", produces = "application/json")
    public ResponseEntity<ProductOutbound> getProductOutboundById(@PathVariable("productOutboundId") String productOutboundId) {
        return ResponseCreator.successfulResponse(productOutboundService.getProductOutboundById(productOutboundId));
    }
}
