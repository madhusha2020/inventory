package com.watersolution.inventory.component.management.payment.supplier.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.payment.supplier.model.api.SupplierPaymentList;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;

public interface SupplierPaymentService {

    SupplierPaymentList getAllSupplierPayments();

    SupplierPayment getSupplierPaymentById(String paymentId);

    SupplierPayment savePayment(PurchaseOrderItemsList purchaseOrderItemsList);

    SupplierPayment updatePayment(SupplierPayment supplierPayment);

    SupplierPayment approvePayment(SupplierPayment supplierPayment);

    SupplierPayment rejectPayment(SupplierPayment supplierPayment);
}
