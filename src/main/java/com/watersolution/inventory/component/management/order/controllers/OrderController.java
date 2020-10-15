package com.watersolution.inventory.component.management.order.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.order.model.Order;
import com.watersolution.inventory.component.management.order.model.OrderItemsList;
import com.watersolution.inventory.component.management.order.model.OrderList;
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
    public ResponseEntity<OrderItemsList> saveCustomer(@RequestBody OrderItemsList orderItemsList) {
        return ResponseCreator.successfulResponse(orderService.placeOrder(orderItemsList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get orders by customerId", response = OrderList.class)
    @CrossOrigin
    @GetMapping(value = "/customer/{customerId}", produces = "application/json")
    public ResponseEntity<OrderList> getOrdersByCustomer(@PathVariable("customerId") long customerId) {
        return ResponseCreator.successfulResponse(orderService.getOrdersByCustomer(customerId));
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
