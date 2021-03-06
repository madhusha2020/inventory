package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryList;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.repository.InventoryRepository;
import com.watersolution.inventory.component.management.notification.service.NotificationService;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;
import com.watersolution.inventory.component.management.supplier.refund.service.SupplierRefundService;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;
import com.watersolution.inventory.component.management.supplier.returns.service.SupplierReturnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SupplierRefundService supplierRefundService;
    @Autowired
    private SupplierReturnService supplierReturnService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public InventoryList getAllItems() {
        return new InventoryList(inventoryRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Override
    public Inventory getByItemId(Long itemId) {
        return inventoryRepository.findByIdAndStatus(itemId, Status.ACTIVE.getValue());
    }

    @Override
    public void preOrderValidate(List<OrderItems> orderItems) {
        orderItems.stream().forEach(orderItem -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(orderItem.getItem().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            if (inventory.getQty() < orderItem.getQty()) {
                final String errorMessage = "Insufficient quantity of item {0} on inventory".replace("{0}", orderItem.getItem().getName());
                throw new CustomException(ErrorCodes.BAD_REQUEST, errorMessage, Collections.singletonList(errorMessage));
            }
        });
    }

    @Override
    public void pendingOrderUpdateInventory(List<OrderItems> orderItems) {
        orderItems.stream().forEach(orderItem -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(orderItem.getItem().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            inventory.setQty(inventory.getQty() - orderItem.getQty());
            inventory.fillUpdateCompulsory(orderItem.getCreatedby());
            if (inventory.getQty() <= orderItem.getItem().getRop()) {
                log.info("Item is about to out of stock");
                notificationService.inventoryNotification(inventory);
            }
            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void rejectedOrderUpdateInventory(List<OrderItems> orderItems) {
        orderItems.stream().forEach(orderItem -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(orderItem.getItem().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            inventory.setQty(inventory.getQty() + orderItem.getQty());
            inventory.fillUpdateCompulsory(orderItem.getCreatedby());
            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void preDisposalValidate(List<DisposalInventory> disposalInventories) {
        disposalInventories.stream().forEach(disposalInventory -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(disposalInventory.getInventory().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            if (inventory.getQty() < disposalInventory.getQty()) {
                final String errorMessage = "Insufficient quantity of item {0} on inventory".replace("{0}", inventory.getItem().getName());
                throw new CustomException(ErrorCodes.BAD_REQUEST, errorMessage, Collections.singletonList(errorMessage));
            }
        });
    }

    @Override
    public void pendingDisposalUpdateInventory(List<DisposalInventory> disposalInventories) {
        disposalInventories.stream().forEach(disposalInventory -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(disposalInventory.getInventory().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            inventory.setDisposedQty(disposalInventory.getQty());

            if (inventory.getQty() >= disposalInventory.getQty()) {
                if (inventory.getQty() > disposalInventory.getQty()) {
                    log.info("Disposal item");
                    notificationService.disposalInventoryNotification(inventory);
                } else if (inventory.getQty() == disposalInventory.getQty()) {
                    log.info("Disposal item");
                    notificationService.disposalInventoryNotification(inventory);
                    log.info("Item is about to out of stock");
                    notificationService.inventoryNotification(inventory);
                }
                inventory.setQty(inventory.getQty() - disposalInventory.getQty());
                inventory.fillUpdateCompulsory(disposalInventory.getCreatedby());
            }

            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void rejectDisposalUpdateInventory(List<DisposalInventory> disposalInventories) {
        disposalInventories.stream().forEach(disposalInventory -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(disposalInventory.getInventory().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            inventory.setDisposedQty(disposalInventory.getQty());
            inventory.setQty(inventory.getQty() + disposalInventory.getQty());
            inventory.fillUpdateCompulsory(disposalInventory.getCreatedby());
            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void pendingPurchaseOrderUpdateInventory(Purchase purchase) {
        /**
         *
         * If applicable Supplier Refund
         * If applicable Supplier Return
         */
        List<SupplierRefundInventory> supplierRefundInventoryList = new ArrayList<>();
        List<SupplierReturnInventory> supplierReturnInventoryList = new ArrayList<>();

        purchase.getPurchaseItems().stream().forEach(purchaseOrderItem -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(purchaseOrderItem.getPurchaseItemId().getItemId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            inventory.setQty(inventory.getQty() + purchaseOrderItem.getAcceptedqty());
            inventory.setInitqty(inventory.getInitqty() + purchaseOrderItem.getAcceptedqty());
            inventory.setDoexpire(purchaseOrderItem.getDoexpire());
            inventory.fillUpdateCompulsory(purchaseOrderItem.getCreatedby());
            inventoryRepository.save(inventory);

            if (purchaseOrderItem.getRejectedqty() != 0) {
                setRefundAndReturns(purchaseOrderItem, supplierRefundInventoryList, supplierReturnInventoryList);
            }
        });

        if (!supplierRefundInventoryList.isEmpty()) {
            supplierRefundService.saveAllSupplierRefundInventories(supplierRefundInventoryList, purchase);
        }
        if (!supplierReturnInventoryList.isEmpty()) {
            supplierReturnService.saveAllSupplierReturnInventories(supplierReturnInventoryList, purchase);
        }
    }

    private void setRefundAndReturns(PurchaseItems purchaseOrderItem, List<SupplierRefundInventory> supplierRefundInventoryList, List<SupplierReturnInventory> supplierReturnInventoryList) {
        SupplierRefundInventory supplierRefundInventory = new SupplierRefundInventory();
        supplierRefundInventory.setItemId(purchaseOrderItem.getPurchaseItemId().getItemId());
        supplierRefundInventory.setQty(purchaseOrderItem.getRejectedqty());
        supplierRefundInventory.setUnitprice(purchaseOrderItem.getUnitprice());
        supplierRefundInventory.setStatus(Status.ACTIVE.getValue());
        supplierRefundInventory.fillCompulsory(purchaseOrderItem.getCreatedby());
        supplierRefundInventoryList.add(supplierRefundInventory);

        SupplierReturnInventory supplierReturnInventory = new SupplierReturnInventory();
        supplierReturnInventory.setItemId(purchaseOrderItem.getPurchaseItemId().getItemId());
        supplierReturnInventory.setQty(purchaseOrderItem.getRejectedqty());
        supplierReturnInventory.setStatus(Status.ACTIVE.getValue());
        supplierReturnInventory.fillCompulsory(purchaseOrderItem.getCreatedby());
        supplierReturnInventoryList.add(supplierReturnInventory);
    }
}
