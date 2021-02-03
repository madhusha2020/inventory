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

    @ApiOperation(value = "Order report by customer", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/order/customer", produces = "application/json")
    public ResponseEntity<ReportResponse> orderReportByCustomer(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.orderReportByCustomer(reportRequest));
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

    @ApiOperation(value = "Purchase order report by supplier", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/purchase-order/supplier", produces = "application/json")
    public ResponseEntity<ReportResponse> purchaseOrderReportBySupplier(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.purchaseOrderReportBySupplier(reportRequest));
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

    @ApiOperation(value = "Purchase report by supplier", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/purchase/supplier", produces = "application/json")
    public ResponseEntity<ReportResponse> purchaseReportBySupplier(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.purchaseReportBySupplier(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Purchase report by item", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/purchase/item", produces = "application/json")
    public ResponseEntity<ReportResponse> purchaseReportByItem(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.purchaseReportByItem(reportRequest));
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

    @ApiOperation(value = "Supplier payment report by customer", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/customer-payment/customer", produces = "application/json")
    public ResponseEntity<ReportResponse> customerPaymentReportByCustomer(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.customerPaymentReportByCustomer(reportRequest));
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

    @ApiOperation(value = "Supplier payment report by supplier", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-payment/supplier", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierPaymentReportBySupplier(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierPaymentReportBySupplier(reportRequest));
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

    @ApiOperation(value = "Supplier return report by supplier", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-return/supplier", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierReturnReportBySupplier(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierReturnReportBySupplier(reportRequest));
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

    @ApiOperation(value = "Supplier refund report by item", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/supplier-refund/item", produces = "application/json")
    public ResponseEntity<ReportResponse> supplierRefundReportByItem(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.supplierRefundReportByItem(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Delivery report by employee", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/delivery/employee", produces = "application/json")
    public ResponseEntity<ReportResponse> deliveryReportByEmployee(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.deliveryReportByEmployee(reportRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Delivery report by vehicle", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/delivery/vehicle", produces = "application/json")
    public ResponseEntity<ReportResponse> deliveryReportByVehicle(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.deliveryReportByVehicle(reportRequest));
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})

    @ApiOperation(value = "Disposal report by item", response = ReportResponse.class)
    @CrossOrigin
    @PostMapping(value = "/disposal/item", produces = "application/json")
    public ResponseEntity<ReportResponse> disposalReportByItem(@Valid @RequestBody ReportRequest reportRequest) {
        return ResponseCreator.successfulResponse(reportService.disposalReportByItem(reportRequest));
    }
}
