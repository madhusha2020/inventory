package com.watersolution.inventory.component.management.inventory.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.repository.InventoryRepository;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CustomValidator customValidator;


    @Override
    public Inventory getByItemId(Long itemId) {
        return inventoryRepository.findByIdAndStatus(itemId, Status.ACTIVE.getValue());
    }

    @Override
    public void pendingOrderUpdateInventory(List<OrderItems> orderItems) {
        orderItems.stream().forEach(orderItem -> {
            Inventory inventory = inventoryRepository.findByIdAndStatus(orderItem.getItem().getId(), Status.ACTIVE.getValue());
            customValidator.validateFoundNull(inventory, "inventory");
            if (inventory.getQty() < orderItem.getQty()) {
                final String errorMessage = "Insufficient quantity of item {0} on inventory".replace("{0}", orderItem.getItem().getName());
                throw new CustomException(ErrorCodes.BAD_REQUEST, errorMessage, Collections.singletonList(errorMessage));
            }
            inventory.setQty(inventory.getQty() - orderItem.getQty());
            inventory.fillUpdateCompulsory(orderItem.getCreatedby());
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
}
