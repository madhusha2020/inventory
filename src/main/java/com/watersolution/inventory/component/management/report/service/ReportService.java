package com.watersolution.inventory.component.management.report.service;

import com.watersolution.inventory.component.management.report.model.ReportRequest;
import com.watersolution.inventory.component.management.report.model.ReportResponse;

public interface ReportService {

    ReportResponse salesReport(ReportRequest reportRequest);

    ReportResponse orderReport(ReportRequest reportRequest);

    ReportResponse purchaseOrderReport(ReportRequest reportRequest);

    ReportResponse purchaseReport(ReportRequest reportRequest);

    ReportResponse customerPaymentReport(ReportRequest reportRequest);

    ReportResponse supplierPaymentReport(ReportRequest reportRequest);

    ReportResponse supplierReturnReport(ReportRequest reportRequest);

    ReportResponse supplierRefundReport(ReportRequest reportRequest);

    ReportResponse deliveryReportByEmployee(ReportRequest reportRequest);

    ReportResponse disposalReport(ReportRequest reportRequest);
}
