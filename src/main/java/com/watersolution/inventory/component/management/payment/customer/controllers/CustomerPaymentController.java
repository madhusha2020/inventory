package com.watersolution.inventory.component.management.payment.customer.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.payment.customer.model.api.CustomerPaymentList;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import com.watersolution.inventory.component.management.payment.customer.service.CustomerPaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer-payment")
public class CustomerPaymentController {

    @Autowired
    private CustomerPaymentService customerPaymentService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available customer payments", response = CustomerPaymentList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<CustomerPaymentList> getAllCustomerPayments() {
        return ResponseCreator.successfulResponse(customerPaymentService.getAllCustomerPayments());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get customer payment by id", response = CustomerPayment.class)
    @CrossOrigin
    @GetMapping(value = "/{paymentId}", produces = "application/json")
    public ResponseEntity<CustomerPayment> getCustomerPaymentById(@PathVariable("paymentId") String paymentId) {
        return ResponseCreator.successfulResponse(customerPaymentService.getCustomerPaymentById(paymentId));
    }
}
