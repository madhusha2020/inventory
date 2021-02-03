package com.watersolution.inventory.component.management.report.service;

import com.watersolution.inventory.component.management.report.model.ReportRequest;
import com.watersolution.inventory.component.management.report.model.ReportResponse;

public interface ReportService {

    ReportResponse salesReport(ReportRequest reportRequest);

    ReportResponse orderReport(ReportRequest reportRequest);

    ReportResponse orderReportByCustomer(ReportRequest reportRequest);

    ReportResponse purchaseOrderReport(ReportRequest reportRequest);

    ReportResponse purchaseOrderReportBySupplier(ReportRequest reportRequest);

    ReportResponse purchaseReport(ReportRequest reportRequest);

    ReportResponse purchaseReportBySupplier(ReportRequest reportRequest);

    ReportResponse purchaseReportByItem(ReportRequest reportRequest);

    ReportResponse customerPaymentReport(ReportRequest reportRequest);

    ReportResponse customerPaymentReportByCustomer(ReportRequest reportRequest);

    ReportResponse supplierPaymentReport(ReportRequest reportRequest);

    ReportResponse supplierPaymentReportBySupplier(ReportRequest reportRequest);

    ReportResponse supplierReturnReport(ReportRequest reportRequest);

    ReportResponse supplierReturnReportBySupplier(ReportRequest reportRequest);

    ReportResponse supplierRefundReport(ReportRequest reportRequest);

    ReportResponse deliveryReportByEmployee(ReportRequest reportRequest);

    ReportResponse deliveryReportByVehicle(ReportRequest reportRequest);

    ReportResponse supplierRefundReportByItem(ReportRequest reportRequest);

    ReportResponse disposalReport(ReportRequest reportRequest);

    ReportResponse disposalReportByItem(ReportRequest reportRequest);
}
