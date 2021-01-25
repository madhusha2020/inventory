package com.watersolution.inventory.component.management.grn.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import com.watersolution.inventory.component.entity.item.service.ItemService;
import com.watersolution.inventory.component.management.grn.model.api.PurchaseItemsList;
import com.watersolution.inventory.component.management.grn.model.api.PurchaseList;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItemId;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import com.watersolution.inventory.component.management.grn.repository.PurchaseItemsRepository;
import com.watersolution.inventory.component.management.grn.repository.PurchaseRepository;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.payment.supplier.service.SupplierPaymentService;
import com.watersolution.inventory.component.management.product.inbound.service.ProductInboundService;
import com.watersolution.inventory.component.management.purchase.model.api.PurchaseOrderItemsList;
import com.watersolution.inventory.component.management.purchase.service.PurchaseOrderService;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private SupplierPaymentService supplierPaymentService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductInboundService productInboundService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseItemsRepository purchaseItemsRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public PurchaseList getAllPurchases() {
        return new PurchaseList(purchaseRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapPurchaseDetails).collect(Collectors.toList()));
    }

    @Override
    public Purchase getPurchaseById(String purchaseId) {
        return mapPurchaseDetails(purchaseRepository.findByIdAndStatusIn(Long.valueOf(purchaseId), Status.getAllStatusAsList()));
    }

    @Transactional
    @Override
    public PurchaseItemsList updatePurchase(PurchaseItemsList purchaseItemsList) {

        Purchase purchase = purchaseRepository.findByIdAndStatus(purchaseItemsList.getPurchase().getId(), Status.PENDING.getValue());
        customValidator.validateFoundNull(purchase.getPurchaseOrder(), "purchase");
        Status.validateState("Purchase Order", purchase.getPurchaseOrder().getStatus(), Status.AWAITING);

        for (PurchaseItems savedPurchaseItem : purchase.getPurchaseItems()) {
            purchaseItemsList.getPurchase().getPurchaseItems().stream().forEach(purchaseItem -> {
                if (savedPurchaseItem.getItem().getId() == purchaseItem.getPurchaseItemId().getItemId()) {
                    savedPurchaseItem.setAcceptedqty(purchaseItem.getAcceptedqty());
                    savedPurchaseItem.setRejectedqty(purchaseItem.getRejectedqty());
                    savedPurchaseItem.setDoexpire(purchaseItem.getDoexpire());
                    savedPurchaseItem.fillUpdateCompulsory(purchaseItem.getUserId());
                }
            });
        }
        /**
         * Update Purchase
         * Update Purchase Order
         * Update Inventory
         * Update Product Inbound
         */
        purchase.setDate(LocalDate.now());
        purchase.setStatus(Status.ACTIVE.getValue());
        purchase.fillUpdateCompulsory(purchaseItemsList.getPurchase().getUserId());
        purchaseRepository.save(purchase);

        purchase.getPurchaseOrder().setDorecived(LocalDate.now());
        purchase.getPurchaseOrder().setStatus(Status.ACTIVE.getValue());
        purchase.getPurchaseOrder().fillUpdateCompulsory(purchaseItemsList.getPurchase().getUserId());
        purchaseOrderService.updatePurchaseOrder(purchase.getPurchaseOrder());

        inventoryService.pendingPurchaseOrderUpdateInventory(purchase);
        productInboundService.updateProductInbound(purchase);

        return purchaseItemsList;
    }

    @Transactional
    @Override
    public Purchase savePurchase(PurchaseOrderItemsList purchaseOrderItemsList) {

        /**
         * Purchase Save
         * Update Payment
         */
        Purchase purchase = new Purchase();
        purchase.setTotal(BigDecimal.ZERO);
        purchase.setCode(purchaseOrderItemsList.getPurchaseOrder().getCode());
        purchase.setDescription(purchaseOrderItemsList.getPurchaseOrder().getDescription());
        purchase.setStatus(Status.PENDING.getValue());
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

    private Purchase mapPurchaseDetails(Purchase purchase) {
        purchase.getPurchaseItems().stream().forEach(purchaseItems -> {
            purchaseItems.setItemName(purchaseItems.getItem().getName());
        });
        return purchase;
    }
}
