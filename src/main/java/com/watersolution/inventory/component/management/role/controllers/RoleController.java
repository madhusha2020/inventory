package com.watersolution.inventory.component.management.role.controllers;

import com.sun.istack.NotNull;
import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.SearchFilter;
import com.watersolution.inventory.component.management.role.model.api.PrivilegeList;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available privileges", response = PrivilegeList.class)
    @CrossOrigin
    @GetMapping(value = "/privilege", produces = "application/json")
    public ResponseEntity<PrivilegeList> getAllPrivileges(@NotNull @Min(0) @ApiParam(value = "The number of items to skip before starting to collect the result set.", required = true, defaultValue = "0") @RequestParam(value = "offset", required = true, defaultValue = "0") Integer offset, @NotNull @Min(1) @Max(100) @ApiParam(value = "The numbers of items to return.", required = true, defaultValue = "10") @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        return ResponseCreator.successfulResponse(roleService.getAllPrivileges(new PageDetails(new SearchFilter(), offset, limit)));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save role", response = Role.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        return ResponseCreator.successfulResponse(roleService.saveRole(role));
    }
}
