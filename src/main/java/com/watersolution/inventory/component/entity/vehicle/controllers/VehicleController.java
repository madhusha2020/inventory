package com.watersolution.inventory.component.entity.vehicle.controllers;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.vehicle.model.api.FacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleFacilityList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleList;
import com.watersolution.inventory.component.entity.vehicle.model.api.VehicleTypeList;
import com.watersolution.inventory.component.entity.vehicle.model.db.Vehicle;
import com.watersolution.inventory.component.entity.vehicle.model.db.VehicleFacility;
import com.watersolution.inventory.component.entity.vehicle.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available vehicles", response = VehicleList.class)
    @CrossOrigin
    @GetMapping(value = "/vehicles", produces = "application/json")
    public ResponseEntity<VehicleList> getAllVehicles() {
        return ResponseCreator.successfulResponse(vehicleService.getAllVehicles());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available vehicle facilities", response = FacilityList.class)
    @CrossOrigin
    @GetMapping(value = "/facilities", produces = "application/json")
    public ResponseEntity<FacilityList> getAllVehicleFacilities() {
        return ResponseCreator.successfulResponse(vehicleService.getAllVehicleFacilities());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "View a list of available vehicle types", response = VehicleTypeList.class)
    @CrossOrigin
    @GetMapping(value = "/types", produces = "application/json")
    public ResponseEntity<VehicleTypeList> getAllVehicleTypes() {
        return ResponseCreator.successfulResponse(vehicleService.getAllVehicleTypes());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save vehicle", response = VehicleFacilityList.class)
    @CrossOrigin
    @PostMapping(value = "/vehicle", produces = "application/json")
    public ResponseEntity<VehicleFacilityList> saveVehicle(@RequestBody VehicleFacilityList vehicleFacilityList) {
        return ResponseCreator.successfulResponse(vehicleService.saveVehicle(vehicleFacilityList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Save vehicle facility", response = VehicleFacility.class)
    @CrossOrigin
    @PostMapping(value = "/facility", produces = "application/json")
    public ResponseEntity<VehicleFacility> saveVehicleFacility(@RequestBody VehicleFacility vehicleFacility) {
        return ResponseCreator.successfulResponse(vehicleService.saveVehicleFacility(vehicleFacility));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get vehicle by id", response = Vehicle.class)
    @CrossOrigin
    @GetMapping(value = "/vehicle/{vehicleId}", produces = "application/json")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable("vehicleId") String vehicleId) {
        return ResponseCreator.successfulResponse(vehicleService.getVehicle(vehicleId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Get vehicle facility by id", response = VehicleFacility.class)
    @CrossOrigin
    @GetMapping(value = "/facility/{facilityId}", produces = "application/json")
    public ResponseEntity<VehicleFacility> getVehicleFacility(@PathVariable("facilityId") String facilityId) {
        return ResponseCreator.successfulResponse(vehicleService.getVehicleFacility(facilityId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update vehicle", response = VehicleFacilityList.class)
    @CrossOrigin
    @PutMapping(value = "/vehicle", produces = "application/json")
    public ResponseEntity<VehicleFacilityList> updateVehicle(@RequestBody VehicleFacilityList vehicleFacilityList) {
        return ResponseCreator.successfulResponse(vehicleService.updateVehicle(vehicleFacilityList));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Update vehicle facility", response = VehicleFacility.class)
    @CrossOrigin
    @PutMapping(value = "/facility", produces = "application/json")
    public ResponseEntity<VehicleFacility> updateVehicleFacility(@RequestBody VehicleFacility vehicleFacility) {
        return ResponseCreator.successfulResponse(vehicleService.updateVehicleFacility(vehicleFacility));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Suspend vehicle", response = Vehicle.class)
    @CrossOrigin
    @PutMapping(value = "/suspend", produces = "application/json")
    public ResponseEntity<Vehicle> suspendVehicle(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(vehicleService.suspendVehicle(transactionRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Activate vehicle", response = Vehicle.class)
    @CrossOrigin
    @PutMapping(value = "/activate", produces = "application/json")
    public ResponseEntity<Vehicle> activateVehicle(@RequestBody TransactionRequest transactionRequest) {
        return ResponseCreator.successfulResponse(vehicleService.activateVehicle(transactionRequest));
    }
}
