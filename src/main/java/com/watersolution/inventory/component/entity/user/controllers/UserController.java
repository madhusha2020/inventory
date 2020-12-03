package com.watersolution.inventory.component.entity.user.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
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

    @ApiOperation(value = "Get all users", response = UserList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<UserList> getAllUsers() {
        return ResponseCreator.successfulResponse(userService.getAllUsers());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get all active users", response = UserList.class)
    @CrossOrigin
    @GetMapping(value = "/active", produces = "application/json")
    public ResponseEntity<UserList> getAllActiveUsers() {
        return ResponseCreator.successfulResponse(userService.getAllActiveUsers());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get customer by id", response = CustomerUser.class)
    @CrossOrigin
    @GetMapping(path = {"/customer/{id}"}, produces = "application/json")
    public ResponseEntity<CustomerUser> getCustomerById(@PathVariable("id") String id) {
        return ResponseCreator.successfulResponse(userService.getCustomerById(id));
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

    @ApiOperation(value = "Update customer", response = CustomerUser.class)
    @CrossOrigin
    @PutMapping(value = "/customer",produces = "application/json")
    public ResponseEntity<CustomerUser> updateCustomer(@Valid @RequestBody CustomerUser customerUser) {
        return ResponseCreator.successfulResponse(userService.updateCustomer(customerUser));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get employee by id", response = EmployeeUser.class)
    @CrossOrigin
    @GetMapping(path = {"/employee/{id}"}, produces = "application/json")
    public ResponseEntity<EmployeeUser> getEmployeeById(@PathVariable("id") String id) {
        return ResponseCreator.successfulResponse(userService.getEmployeeById(id));
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update employee", response = EmployeeUser.class)
    @CrossOrigin
    @PutMapping(value = "/employee", produces = "application/json")
    public ResponseEntity<EmployeeUser> updateEmployee(@Valid @RequestBody EmployeeUser employeeUser) {
        return ResponseCreator.successfulResponse(userService.updateEmployee(employeeUser));
    }
}
