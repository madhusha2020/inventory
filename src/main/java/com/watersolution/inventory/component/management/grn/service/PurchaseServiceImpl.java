package com.watersolution.inventory.component.management.grn.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.entity.item.service.ItemService;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItemId;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import com.watersolution.inventory.component.management.grn.repository.PurchaseItemsRepository;
import com.watersolution.inventory.component.management.grn.repository.PurchaseRepository;
import com.watersolution.inventory.component.management.payment.supplier.service.SupplierPaymentService;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SupplierPaymentService supplierPaymentService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseItemsRepository purchaseItemsRepository;

    @Override
    public Purchase savePurchase(PurchaseOrderItemsList purchaseOrderItemsList) {

        Purchase purchase = new Purchase();
        purchase.setTotal(BigDecimal.ZERO);
        purchase.setCode(purchaseOrderItemsList.getPurchaseOrder().getCode());
        purchase.setDescription(purchaseOrderItemsList.getPurchaseOrder().getDescription());
        purchase.setStatus(Status.ACTIVE.getValue());
        purchase.fillCompulsory(purchaseOrderItemsList.getUserId());

        purchase.setPurchaseOrder(purchaseOrderItemsList.getPurchaseOrder());
        purchase.setSupplier(purchaseOrderItemsList.getPurchaseOrder().getSupplier());
        purchase.setSupplierPayment(purchaseOrderItemsList.getSupplierPayment());
        purchaseRepository.save(purchase);

        List<PurchaseItems> purchaseItemsList = new ArrayList<>();
        purchaseOrderItemsList.getPurchaseOrderItems().stream().forEach(purchaseOrderItem -> {

            Item item = itemService.getItemById(String.valueOf(purchaseOrderItem.getItem().getId()));

            PurchaseItems purchaseItems = new PurchaseItems();
            purchaseItems.setPurchaseItemId(new PurchaseItemId(purchase.getId(), item.getId()));
            purchaseItems.setQty(purchaseOrderItem.getQty());
            purchaseItems.setUnitprice(item.getLastprice());
            purchaseItems.setStatus(Status.ACTIVE.getValue());
            purchaseItems.fillCompulsory(purchase.getCreatedby());

            purchaseItems.setPurchase(purchase);
            purchaseItems.setItem(item);
            purchase.setTotal(purchase.getTotal().add(new BigDecimal(purchaseItems.getItem().getLastprice() * purchaseItems.getQty())));
            purchaseItemsList.add(purchaseItems);
        });
        purchaseItemsRepository.saveAll(purchaseItemsList);

        purchase.getSupplierPayment().setAmount(purchase.getTotal());
        purchase.getSupplierPayment().setUserId(purchaseOrderItemsList.getUserId());
        purchaseOrderItemsList.getSupplierPayment().setPurchase(purchase);
        supplierPaymentService.updatePayment(purchaseOrderItemsList.getSupplierPayment());
        return purchase;
    }
}
