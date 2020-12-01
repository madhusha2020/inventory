package com.watersolution.inventory.component.management.notification.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.notification.model.api.NotificationList;
import com.watersolution.inventory.component.management.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of notifications", response = NotificationList.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<NotificationList> getNotificationsByUser(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(notificationService.getNotificationsByUser(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of awaiting notifications", response = NotificationList.class)
    @CrossOrigin
    @PostMapping(value = "/user/awaiting", produces = "application/json")
    public ResponseEntity<NotificationList> getAwaitingNotificationsByUser(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(notificationService.getAwaitingNotificationsByUser(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of new notifications", response = NotificationList.class)
    @CrossOrigin
    @PostMapping(value = "/user/pending", produces = "application/json")
    public ResponseEntity<NotificationList> getNewNotificationsByUser(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(notificationService.getNewNotificationsByUser(transactionRequest));
    }
}


