package com.watersolution.inventory.component.entity.supplier.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.supplier.model.api.SupplierList;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.entity.supplier.service.SupplierService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available suppliers", response = SupplierList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<SupplierList> getAllSuppliers() {
        return ResponseCreator.successfulResponse(supplierService.getAllSuppliers());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available active suppliers", response = SupplierList.class)
    @CrossOrigin
    @GetMapping(value = "/active", produces = "application/json")
    public ResponseEntity<SupplierList> getAllActiveSuppliers() {
        return ResponseCreator.successfulResponse(supplierService.getAllActiveSuppliers());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Suspend supplier", response = Supplier.class)
    @CrossOrigin
    @PutMapping(value = "/suspend", produces = "application/json")
    public ResponseEntity<Supplier> suspendSupplier(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(supplierService.suspendSupplier(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Activate supplier", response = Supplier.class)
    @CrossOrigin
    @PutMapping(value = "/activate", produces = "application/json")
    public ResponseEntity<Supplier> activateSupplier(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(supplierService.activateSupplier(transactionRequest));
    }
}
