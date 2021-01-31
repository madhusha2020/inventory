package com.watersolution.inventory.component.management.report.model;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReportResponse extends ResponseDefault {
    private List<Sale> saleList;
    private List<Order> orderList;
    private List<PurchaseOrder> purchaseOrderList;
    private List<Purchase> purchaseList;
    private List<CustomerPayment> customerPaymentList;
    private List<SupplierPayment> supplierPaymentList;
    private List<SupplierReturn> supplierReturnList;
    private List<SupplierRefund> supplierRefundList;
    private List<Delivery> deliveryList;
    private List<Disposal> disposalList;
}
