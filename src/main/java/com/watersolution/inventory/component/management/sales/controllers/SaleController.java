package com.watersolution.inventory.component.management.sales.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.sales.model.api.SaleList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.sales.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of sales", response = SaleList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<SaleList> getAllSales() {
        return ResponseCreator.successfulResponse(saleService.getAllSales());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get sale by saleId", response = Sale.class)
    @CrossOrigin
    @GetMapping(value = "/{saleId}", produces = "application/json")
    public ResponseEntity<Sale> getSaleById(@PathVariable("saleId") String saleId) {
        return ResponseCreator.successfulResponse(saleService.getSaleById(saleId));
    }
}
