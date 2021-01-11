package com.watersolution.inventory.component.management.payment.supplier.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.payment.supplier.model.api.SupplierPaymentList;
import com.watersolution.inventory.component.management.payment.supplier.service.SupplierPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("supplier-payment")
public class SupplierPaymentController {

    @Autowired
    private SupplierPaymentService supplierPaymentService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available supplier payments", response = SupplierPaymentList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<SupplierPaymentList> getAllCustomerPayments() {
        return ResponseCreator.successfulResponse(supplierPaymentService.getAllSupplierPayments());
    }
}
