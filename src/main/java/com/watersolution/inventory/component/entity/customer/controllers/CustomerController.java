package com.watersolution.inventory.component.entity.customer.controllers;

import com.sun.istack.NotNull;
import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.SearchFilter;
import com.watersolution.inventory.component.entity.customer.model.api.CustomerList;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available customers", response = CustomerList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<CustomerList> getAllCustomers(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(customerService.getAllCustomers(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Search customers", response = CustomerList.class)
    @CrossOrigin
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<CustomerList> searchCustomers(@RequestBody SearchFilter searchFilter, @NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(customerService.searchCustomers(new PageDetails(searchFilter, offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save customer", response = Customer.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        return ResponseCreator.successfulResponse(customerService.saveCustomer(customer));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update customer", response = Customer.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        return ResponseCreator.successfulResponse(customerService.updateCustomer(customer));
    }
}
