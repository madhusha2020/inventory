package com.watersolution.inventory.component.management.supplier.refund.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.supplier.refund.model.api.SupplierRefundList;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import com.watersolution.inventory.component.management.supplier.refund.service.SupplierRefundService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("supplier-refund")
public class SupplierRefundController {

    @Autowired
    private SupplierRefundService supplierRefundService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available supplier refunds", response = SupplierRefundList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<SupplierRefundList> getAllSupplierRefunds() {
        return ResponseCreator.successfulResponse(supplierRefundService.getAllSupplierRefunds());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get supplier refund by id", response = SupplierRefund.class)
    @CrossOrigin
    @GetMapping(value = "/{supplierRefundId}", produces = "application/json")
    public ResponseEntity<SupplierRefund> getSupplierRefundById(@PathVariable("supplierRefundId") String supplierRefundId) {
        return ResponseCreator.successfulResponse(supplierRefundService.getSupplierRefundById(supplierRefundId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update supplier refund", response = SupplierRefund.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<SupplierRefund> updateSupplierRefund(@Valid @RequestBody SupplierRefund supplierRefund) {
        return ResponseCreator.successfulResponse(supplierRefundService.updateSupplierRefund(supplierRefund));
    }
}
