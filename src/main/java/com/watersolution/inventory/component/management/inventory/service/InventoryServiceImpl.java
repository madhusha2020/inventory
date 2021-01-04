package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.inventory.model.api.InventoryList;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.repository.InventoryRepository;
import com.watersolution.inventory.component.management.notification.service.NotificationService;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private NotificationService notificationService;
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
    public void disposalUpdateInventory(List<DisposalInventory> disposalInventories) {
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
}
