package com.watersolution.inventory.component.entity.discount.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.entity.discount.model.api.TransactionDetails;
import com.watersolution.inventory.component.entity.discount.model.db.Discount;
import com.watersolution.inventory.component.entity.discount.service.DiscountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get discount by category and type", response = Discount.class)
    @CrossOrigin
    @GetMapping(value = "/{category}/{type}", produces = "application/json")
    public ResponseEntity<Discount> getDiscount(@PathVariable("category") String category, @PathVariable("type") String type) {
        return ResponseCreator.successfulResponse(discountService.getDiscount(category, type));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get calculated transaction details", response = TransactionDetails.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<TransactionDetails> getTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        return ResponseCreator.successfulResponse(discountService.getTransactionDetails(transactionDetails));
    }
}
