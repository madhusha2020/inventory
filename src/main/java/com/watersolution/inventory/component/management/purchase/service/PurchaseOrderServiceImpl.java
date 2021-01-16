package com.watersolution.inventory.component.management.purchase.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.entity.supplier.service.SupplierService;
import com.watersolution.inventory.component.management.grn.service.PurchaseService;
import com.watersolution.inventory.component.management.notification.service.NotificationService;
import com.watersolution.inventory.component.management.payment.supplier.service.SupplierPaymentService;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderList;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrderItemId;
import com.watersolution.inventory.component.management.purchase.repository.PurchaseOrderItemsRepository;
import com.watersolution.inventory.component.management.purchase.repository.PurchaseOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierPaymentService supplierPaymentService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseOrderItemsRepository purchaseOrderItemsRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public PurchaseOrderList getAllPurchaseOrders() {
        return new PurchaseOrderList(purchaseOrderRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapPurchaseOrderDetails).collect(Collectors.toList()));
    }

    @Override
    public PurchaseOrderList getOrdersBySupplier(TransactionRequest transactionRequest) {
        return new PurchaseOrderList(purchaseOrderRepository.findBySupplier_IdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList()).stream().map(this::mapPurchaseOrderDetails).collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public PurchaseOrderItemsList placePurchaseOrder(PurchaseOrderItemsList purchaseOrderItemsList) {

        Supplier supplier = supplierService.getSupplier(String.valueOf(purchaseOrderItemsList.getPurchaseOrder().getSupplierId()));
        customValidator.validateFoundNull(supplier, "supplier");

        purchaseOrderItemsList.getPurchaseOrder().setSupplier(supplier);
        purchaseOrderItemsList.getPurchaseOrder().setDoordered(LocalDate.now());
        purchaseOrderItemsList.getPurchaseOrder().fillCompulsory(purchaseOrderItemsList.getUserId());
        purchaseOrderItemsList.getPurchaseOrder().setStatus(Status.PENDING.getValue());

        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(purchaseOrderItemsList.getPurchaseOrder());

        purchaseOrderItemsList.getPurchaseOrderItems().stream().forEach(orderItem -> {
            orderItem.setPurchaseOrder(purchaseOrder);
            orderItem.setPurchaseOrderItemId(new PurchaseOrderItemId(orderItem.getPurchaseOrder().getId(), orderItem.getItem().getId()));
            orderItem.fillCompulsory(purchaseOrderItemsList.getUserId());
            orderItem.setStatus(Status.ACTIVE.getValue());
        });

        /**
         * Purchase Order Init
         * Payment Init
         * Purchase Init
         * Notification Init
         */
        purchaseOrderItemsRepository.saveAll(purchaseOrderItemsList.getPurchaseOrderItems());
        supplierPaymentService.savePayment(purchaseOrderItemsList);
        purchaseService.savePurchase(purchaseOrderItemsList);
        notificationService.purchaseOrderNotification(purchaseOrder);

        return purchaseOrderItemsList;
    }

    @Transactional
    @Override
    public PurchaseOrder approvePurchaseOrder(TransactionRequest transactionRequest) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(transactionRequest.getId());
        customValidator.validateFoundNull(purchaseOrder, "purchase order");
        Status.validateState("purchase order", purchaseOrder.getStatus(), Status.PENDING);
        purchaseOrder.setStatus(Status.AWAITING.getValue());
        purchaseOrder.fillUpdateCompulsory(transactionRequest.getUserId());
        purchaseOrder.getPurchase().getSupplierPayment().fillUpdateCompulsory(transactionRequest.getUserId());

        /**
         * Order Update
         * Notification Init
         */
        purchaseOrderRepository.save(purchaseOrder);
        supplierPaymentService.approvePayment(purchaseOrder.getPurchase().getSupplierPayment());
        notificationService.supplierNotification(purchaseOrder);

        return mapPurchaseOrderDetails(purchaseOrder);
    }

    @Transactional
    @Override
    public PurchaseOrder rejectPurchaseOrder(TransactionRequest transactionRequest) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(transactionRequest.getId());
        customValidator.validateFoundNull(purchaseOrder, "purchase order");
        Status.validateState("purchase order", purchaseOrder.getStatus(), Status.PENDING);
        purchaseOrder.setStatus(Status.REJECTED.getValue());
        purchaseOrder.fillUpdateCompulsory(transactionRequest.getUserId());
        purchaseOrder.getPurchase().getSupplierPayment().fillUpdateCompulsory(transactionRequest.getUserId());

        /**
         * Order Update
         */
        purchaseOrderRepository.save(purchaseOrder);
        supplierPaymentService.rejectPayment(purchaseOrder.getPurchase().getSupplierPayment());

        return mapPurchaseOrderDetails(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(String purchaseOrderId) {
        return mapPurchaseOrderDetails(purchaseOrderRepository.findByIdAndStatusIn(Long.valueOf(purchaseOrderId), Status.getAllStatusAsList()));
    }

    private PurchaseOrder mapPurchaseOrderDetails(PurchaseOrder purchaseOrder) {
        return purchaseOrder;
    }
}
