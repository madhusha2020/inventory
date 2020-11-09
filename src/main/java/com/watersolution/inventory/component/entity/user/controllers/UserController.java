package com.watersolution.inventory.component.entity.user.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.api.EmployeeUser;
import com.watersolution.inventory.component.entity.user.model.api.UserList;
import com.watersolution.inventory.component.entity.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get All Users", response = UserList.class)
    @CrossOrigin
    @GetMapping
    public ResponseEntity<UserList> getAllUsers() {
        return ResponseCreator.successfulResponse(userService.getAllUsers());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save customer", response = CustomerUser.class)
    @CrossOrigin
    @PostMapping(value = "/customer", produces = "application/json")
    public ResponseEntity<CustomerUser> saveCustomer(@Valid @RequestBody CustomerUser customerUser) {
        return ResponseCreator.successfulResponse(userService.saveCustomer(customerUser));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save employee", response = EmployeeUser.class)
    @CrossOrigin
    @PostMapping(value = "/employee", produces = "application/json")
    public ResponseEntity<EmployeeUser> saveEmployee(@Valid @RequestBody EmployeeUser employeeUser) {
        return ResponseCreator.successfulResponse(userService.saveEmployee(employeeUser));
    }
}
