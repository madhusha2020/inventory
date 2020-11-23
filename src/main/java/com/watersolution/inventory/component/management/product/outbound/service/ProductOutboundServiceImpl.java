package com.watersolution.inventory.component.management.product.outbound.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.product.outbound.model.api.ProductOutboundList;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutbound;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutboundItem;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutboundItemId;
import com.watersolution.inventory.component.management.product.outbound.repository.ProductOutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOutboundServiceImpl implements ProductOutboundService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductOutboundRepository productOutboundRepository;

    @Override
    public ProductOutboundList getAllProductOutbounds() {
        return new ProductOutboundList(productOutboundRepository.findAllByStatusIn(Status.getAllStatusAsList()));
    }

    @Override
    public void updateProductOutbound(Order order) {
        ProductOutbound productOutbound = new ProductOutbound();
        List<ProductOutboundItem> productOutboundItems = new ArrayList<>();

        productOutbound.setCode("");
        productOutbound.setDescription("");
        productOutbound.setDate(LocalDateTime.now());
        productOutbound.setStatus(Status.ACTIVE.getValue());
        productOutbound.fillCompulsory(order.getCreatedby());
        productOutboundRepository.save(productOutbound);

        order.getOrderItems().stream().forEach(orderItem -> {
            ProductOutboundItem productOutboundItem = new ProductOutboundItem();
            productOutboundItem.setProductOutbound(productOutbound);
            productOutboundItem.setProductOutboundItemId(new ProductOutboundItemId(productOutbound.getId(), orderItem.getItem().getId()));
            productOutboundItem.setItem(orderItem.getItem());
            productOutboundItem.setQty(orderItem.getQty());
            productOutboundItem.setDoexpire(inventoryService.getByItemId(orderItem.getItem().getId()).getDoexpire());
            productOutboundItem.setStatus(Status.ACTIVE.getValue());
            productOutboundItem.fillCompulsory(order.getCreatedby());
            productOutboundItems.add(productOutboundItem);
        });
        productOutbound.setProductOutboundItems(productOutboundItems);
        productOutboundRepository.save(productOutbound);
    }
}
