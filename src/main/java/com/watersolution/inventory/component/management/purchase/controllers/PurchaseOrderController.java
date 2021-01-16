package com.watersolution.inventory.component.management.purchase.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderList;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.purchase.service.PurchaseOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchase-order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available purchase orders", response = PurchaseOrderList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<PurchaseOrderList> getAllPurchaseOrders() {
        return ResponseCreator.successfulResponse(purchaseOrderService.getAllPurchaseOrders());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all orders by supplierId", response = PurchaseOrderList.class)
    @CrossOrigin
    @PostMapping(value = "/supplier", produces = "application/json")
    public ResponseEntity<PurchaseOrderList> getOrdersBySupplier(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(purchaseOrderService.getOrdersBySupplier(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Place purchase order", response = PurchaseOrderItemsList.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<PurchaseOrderItemsList> placePurchaseOrder(@RequestBody PurchaseOrderItemsList purchaseOrderItemsList) {
        return ResponseCreator.successfulResponse(purchaseOrderService.placePurchaseOrder(purchaseOrderItemsList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Approve purchase order", response = PurchaseOrder.class)
    @CrossOrigin
    @PutMapping(value = "/approve", produces = "application/json")
    public ResponseEntity<PurchaseOrder> approvePurchaseOrder(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(purchaseOrderService.approvePurchaseOrder(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Reject purchase order", response = PurchaseOrder.class)
    @CrossOrigin
    @PutMapping(value = "/reject", produces = "application/json")
    public ResponseEntity<PurchaseOrder> rejectPurchaseOrder(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(purchaseOrderService.rejectPurchaseOrder(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get purchase orders by orderId", response = PurchaseOrder.class)
    @CrossOrigin
    @GetMapping(value = "/{purchaseOrderId}", produces = "application/json")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable("purchaseOrderId") String purchaseOrderId) {
        return ResponseCreator.successfulResponse(purchaseOrderService.getPurchaseOrderById(purchaseOrderId));
    }
}
