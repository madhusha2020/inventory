package com.watersolution.inventory.component.entity.employee.controllers;

import com.sun.istack.NotNull;
import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.SearchFilter;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.employee.model.api.EmployeeList;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available employees", response = EmployeeList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<EmployeeList> getAllEmployees(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(employeeService.getAllEmployees(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available active employees", response = EmployeeList.class)
    @CrossOrigin
    @GetMapping(value = "/active", produces = "application/json")
    public ResponseEntity<EmployeeList> getAllActiveEmployees(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(employeeService.getAllActiveEmployees(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Search employees", response = EmployeeList.class)
    @CrossOrigin
    @PostMapping(value = "/search", produces = "application/json")
    public ResponseEntity<EmployeeList> searchEmployees(@RequestBody SearchFilter searchFilter, @NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(employeeService.searchEmployees(new PageDetails(searchFilter, offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Employee suspend", response = Employee.class)
    @CrossOrigin
    @PutMapping(path = {"/suspend"}, produces = "application/json")
    public ResponseEntity<Employee> suspendEmployee(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(employeeService.suspendEmployee(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Employee activate", response = Employee.class)
    @CrossOrigin
    @PutMapping(path = {"/activate"}, produces = "application/json")
    public ResponseEntity<Employee> activateEmployee(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(employeeService.activateEmployee(transactionRequest));
    }
}
