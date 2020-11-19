package com.watersolution.inventory.component.management.order.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.api.OrderList;
import com.watersolution.inventory.component.management.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Place customer order", response = OrderItemsList.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<OrderItemsList> placeOrder(@RequestBody OrderItemsList orderItemsList) {
        return ResponseCreator.successfulResponse(orderService.placeOrder(orderItemsList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Approve customer order", response = Order.class)
    @CrossOrigin
    @PostMapping(value = "/approve", produces = "application/json")
    public ResponseEntity<Order> approveOrder(@RequestBody Order order) {
        return ResponseCreator.successfulResponse(orderService.approveOrder(order));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all orders", response = OrderList.class)
    @CrossOrigin
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<OrderList> getAllOrders() {
        return ResponseCreator.successfulResponse(orderService.getAllOrders());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all active orders", response = OrderList.class)
    @CrossOrigin
    @GetMapping(value = "/all/active", produces = "application/json")
    public ResponseEntity<OrderList> getAllActiveOrders() {
        return ResponseCreator.successfulResponse(orderService.getAllActiveOrders());
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all orders by customerId", response = OrderList.class)
    @CrossOrigin
    @PostMapping(value = "/customer", produces = "application/json")
    public ResponseEntity<OrderList> getOrdersByCustomer(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(orderService.getOrdersByCustomer(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get active orders by customerId", response = OrderList.class)
    @CrossOrigin
    @PostMapping(value = "/customer/active", produces = "application/json")
    public ResponseEntity<OrderList> getActiveOrdersByCustomer(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(orderService.getActiveOrdersByCustomer(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get orders by orderId", response = Order.class)
    @CrossOrigin
    @GetMapping(value = "/{orderId}", produces = "application/json")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") long orderId) {
        return ResponseCreator.successfulResponse(orderService.getOrderById(orderId));
    }
}
