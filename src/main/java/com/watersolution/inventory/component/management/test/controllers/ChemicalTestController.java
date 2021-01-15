package com.watersolution.inventory.component.management.test.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.test.model.api.ChemicalTestList;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import com.watersolution.inventory.component.management.test.service.ChemicalTestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chemical-test")
public class ChemicalTestController {

    @Autowired
    private ChemicalTestService chemicalTestService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available chemical tests", response = ChemicalTestList.class)
    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<ChemicalTestList> getAllChemicalTests() {
        return ResponseCreator.successfulResponse(chemicalTestService.getAllChemicalTests());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get chemical test by id", response = ChemicalTest.class)
    @CrossOrigin
    @GetMapping(value = "/{testId}", produces = "application/json")
    public ResponseEntity<ChemicalTest> getChemicalTestById(@PathVariable("testId") String testId) {
        return ResponseCreator.successfulResponse(chemicalTestService.getChemicalTestById(testId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update chemical test", response = ChemicalTest.class)
    @CrossOrigin
    @PutMapping(produces = "application/json")
    public ResponseEntity<ChemicalTest> updateChemicalTest(@RequestBody ChemicalTest chemicalTest) {
        return ResponseCreator.successfulResponse(chemicalTestService.updateChemicalTest(chemicalTest));
    }
}
