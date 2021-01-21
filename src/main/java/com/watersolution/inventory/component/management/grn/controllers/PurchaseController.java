package com.watersolution.inventory.component.management.grn.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.grn.model.api.PurchaseItemsList;
import com.watersolution.inventory.component.management.grn.model.api.PurchaseList;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available purchases", response = PurchaseList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<PurchaseList> getAllPurchases() {
        return ResponseCreator.successfulResponse(purchaseService.getAllPurchases());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get purchase by id", response = Purchase.class)
    @CrossOrigin
    @GetMapping(value = "/{purchaseId}", produces = "application/json")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable("purchaseId") String purchaseId) {
        return ResponseCreator.successfulResponse(purchaseService.getPurchaseById(purchaseId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update purchase", response = PurchaseItemsList.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<PurchaseItemsList> updatePurchase(@RequestBody PurchaseItemsList purchaseItemsList) {
        return ResponseCreator.successfulResponse(purchaseService.updatePurchase(purchaseItemsList));
    }
}
