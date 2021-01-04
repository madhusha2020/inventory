package com.watersolution.inventory.component.management.product.inbound.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.product.inbound.model.api.ProductInboundList;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInbound;
import com.watersolution.inventory.component.management.product.inbound.repository.ProductInboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void updateProductInbound() {

    }
}
