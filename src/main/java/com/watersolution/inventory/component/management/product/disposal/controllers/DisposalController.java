package com.watersolution.inventory.component.management.product.disposal.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalInventoryList;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalList;
import com.watersolution.inventory.component.management.product.disposal.service.DisposalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("disposal")
public class DisposalController {

    @Autowired
    private DisposalService disposalService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Pre disposal validate", response = DisposalInventoryList.class)
    @CrossOrigin
    @PostMapping(value = "/predisposal", produces = "application/json")
    public ResponseEntity<DisposalInventoryList> preDisposalValidate(@RequestBody DisposalInventoryList disposalInventoryList) {
        return ResponseCreator.successfulResponse(disposalService.preDisposalValidate(disposalInventoryList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all disposal products", response = DisposalList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<DisposalList> getAllDisposalProducts() {
        return ResponseCreator.successfulResponse(disposalService.getAllDisposalProducts());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save disposal product", response = DisposalInventoryList.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<DisposalInventoryList> createDisposalProduct(@RequestBody DisposalInventoryList disposalInventoryList) {
        return ResponseCreator.successfulResponse(disposalService.createDisposalProduct(disposalInventoryList));
    }
}
