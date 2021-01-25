package com.watersolution.inventory.component.management.supplier.returns.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.supplier.returns.model.api.SupplierReturnList;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import com.watersolution.inventory.component.management.supplier.returns.service.SupplierReturnService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("supplier-return")
public class SupplierReturnController {

    @Autowired
    private SupplierReturnService supplierReturnService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available supplier returns", response = SupplierReturnList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<SupplierReturnList> getAllSupplierReturns() {
        return ResponseCreator.successfulResponse(supplierReturnService.getAllSupplierReturns());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get supplier return by id", response = SupplierReturn.class)
    @CrossOrigin
    @GetMapping(value = "/{supplierReturnId}", produces = "application/json")
    public ResponseEntity<SupplierReturn> getSupplierRefundById(@PathVariable("supplierReturnId") String supplierReturnId) {
        return ResponseCreator.successfulResponse(supplierReturnService.getSupplierReturnById(supplierReturnId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update supplier return", response = SupplierReturn.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<SupplierReturn> updateSupplierReturn(@Valid @RequestBody SupplierReturn supplierReturn) {
        return ResponseCreator.successfulResponse(supplierReturnService.updateSupplierReturn(supplierReturn));
    }
}
