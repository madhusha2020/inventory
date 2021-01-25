package com.watersolution.inventory.component.management.complain.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.complain.model.api.ComplainList;
import com.watersolution.inventory.component.management.complain.model.db.Complain;
import com.watersolution.inventory.component.management.complain.service.ComplainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("complain")
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available complains", response = ComplainList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<ComplainList> getAllComplains() {
        return ResponseCreator.successfulResponse(complainService.getAllComplains());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get complain by id", response = Complain.class)
    @CrossOrigin
    @GetMapping(value = "/{complainId}", produces = "application/json")
    public ResponseEntity<Complain> getComplainById(@PathVariable("complainId") String complainId) {
        return ResponseCreator.successfulResponse(complainService.getComplainById(complainId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save complain", response = Complain.class)
    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<Complain> saveComplain(@Valid @RequestBody Complain complain) {
        return ResponseCreator.successfulResponse(complainService.saveComplain(complain));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update complain", response = Complain.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<Complain> updateComplain(@Valid @RequestBody Complain complain) {
        return ResponseCreator.successfulResponse(complainService.updateComplain(complain));
    }
}
