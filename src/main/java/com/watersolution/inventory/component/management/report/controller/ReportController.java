package com.watersolution.inventory.component.management.report.controller;

import com.watersolution.inventory.component.common.exception.ResponseCreator;
import com.watersolution.inventory.component.management.report.model.ReportRequest;
import com.watersolution.inventory.component.management.report.model.ReportResponse;
import com.watersolution.inventory.component.management.report.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Sales report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/sale", produces = "application/json")
    public ResponseEntity<ReportResponse> salesReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.salesReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Order report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/order", produces = "application/json")
    public ResponseEntity<ReportResponse> orderReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.orderReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Purchase order report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/purchase-order", produces = "application/json")
    public ResponseEntity<ReportResponse> purchaseOrderReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.purchaseOrderReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Purchase report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/purchase", produces = "application/json")
    public ResponseEntity<ReportResponse> purchaseReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.purchaseReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Customer payment report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/customer-payment", produces = "application/json")
    public ResponseEntity<ReportResponse> customerPaymentReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.customerPaymentReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Supplier payment report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-payment", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierPaymentReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierPaymentReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Supplier return report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-return", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierReturnReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierReturnReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Supplier refund report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-refund", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierRefundReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierRefundReport(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Delivery report by employee", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/delivery", produces = "application/json")
    public ResponseEntity<ReportResponse> deliveryReportByEmployee(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.deliveryReportByEmployee(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Disposal report", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/disposal", produces = "application/json")
    public ResponseEntity<ReportResponse> disposalReport(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.disposalReport(reportRequest));
    }
}
