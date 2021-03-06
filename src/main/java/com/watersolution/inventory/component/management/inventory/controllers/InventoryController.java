package com.watersolution.inventory.component.management.inventory.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryList;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available items in the inventory", response = InventoryList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<InventoryList> getAllInventoryItems() {
        return ResponseCreator.successfulResponse(inventoryService.getAllItems());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get inventory by id", response = Inventory.class)
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") String id) {
        return ResponseCreator.successfulResponse(inventoryService.getByItemId(Long.valueOf(id)));
    }
}
