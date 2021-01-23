package com.watersolution.inventory.component.management.product.inbound.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.product.inbound.model.api.ProductInboundList;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInboundItem;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInboundItemId;
import com.watersolution.inventory.component.management.product.inbound.repository.ProductInboundRepository;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInboundServiceImpl implements ProductInboundService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductInboundRepository productInboundRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public ProductInboundList getAllProductInbounds() {
        return new ProductInboundList(productInboundRepository.findAllByStatusIn(Status.getAllStatusAsList()));
    }

    @Override
    public ProductInbound getProductInboundById(String productInboundById) {
        ProductInbound productInbound = productInboundRepository.findByIdAndStatusIn(Long.valueOf(productInboundById), Status.getAllStatusAsList());
        customValidator.validateFoundNull(productInbound, "ProductInbound");
        return productInbound;
    }

    @Override
    public void updateProductInbound(Purchase purchase) {
        ProductInbound productInbound = new ProductInbound();
        List<ProductInboundItem> productInboundItems = new ArrayList<>();

        productInbound.setCode("");
        productInbound.setDescription("");
        productInbound.setDate(LocalDate.now());
        productInbound.setStatus(Status.ACTIVE.getValue());
        productInbound.fillCompulsory(purchase.getCreatedby());
        productInboundRepository.save(productInbound);

        purchase.getPurchaseItems().stream().forEach(orderItem -> {
            ProductInboundItem productInboundItem = new ProductInboundItem();
            productInboundItem.setProductInboundItemId(new ProductInboundItemId(productInbound.getId(), orderItem.getItem().getId()));
            productInboundItem.setItem(orderItem.getItem());
            productInboundItem.setProductInbound(productInbound);
            productInboundItem.setQty(orderItem.getAcceptedqty());
            productInboundItem.setDoexpire(inventoryService.getByItemId(orderItem.getItem().getId()).getDoexpire());
            productInboundItem.setStatus(Status.ACTIVE.getValue());
            productInboundItem.fillCompulsory(purchase.getCreatedby());
            productInboundItems.add(productInboundItem);
        });
        productInbound.setProductInboundItems(productInboundItems);
        productInboundRepository.save(productInbound);
    }
}
