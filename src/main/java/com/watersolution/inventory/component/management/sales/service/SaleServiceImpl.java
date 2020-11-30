package com.watersolution.inventory.component.management.sales.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.sales.model.api.SaleList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.sales.model.db.SaleCustomCompound;
import com.watersolution.inventory.component.management.sales.model.db.SaleInventory;
import com.watersolution.inventory.component.management.sales.model.db.SaleInventoryId;
import com.watersolution.inventory.component.management.sales.repository.SaleRepository;
import com.watersolution.inventory.component.management.sales.util.SaleServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleServiceHelper saleServiceHelper;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public SaleList getAllSales() {
        return new SaleList(saleRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapSaleDetails).collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public Sale saveSale(OrderItemsList orderItemsList) {

        Sale sale = saleServiceHelper.calculateSales(new Sale(), orderItemsList);
        sale.setCode("");
        sale.setDescription("");
        sale.setDate(LocalDate.now());
        sale.setDeliveryaddress(orderItemsList.getOrder().getCustomer().getAddress());
        sale.fillCompulsory(orderItemsList.getUserId());
        sale.setStatus(Status.PENDING.getValue());

        SaleCustomCompound saleCustomCompound = new SaleCustomCompound();
        saleCustomCompound.setName(orderItemsList.getOrder().getCustomer().getName());
        saleCustomCompound.setDescription(orderItemsList.getOrder().getCustomer().getDescription());
        saleCustomCompound.setPrice(sale.getTotal());
        saleCustomCompound.fillCompulsory(orderItemsList.getUserId());
        saleCustomCompound.setStatus(Status.ACTIVE.getValue());
        saleCustomCompound.setSale(sale);

        sale.setCustomer(orderItemsList.getOrder().getCustomer());
        sale.setOrder(orderItemsList.getOrder());
        sale.setSaleCustomCompound(saleCustomCompound);
        saleRepository.save(sale);

        List<SaleInventory> saleInventoryList = new ArrayList<>();

        orderItemsList.getOrderItems().stream().forEach(orderItem -> {
            Inventory inventory = inventoryService.getByItemId(orderItem.getItem().getId());
            customValidator.validateFoundNull(inventory, "inventory");

            SaleInventory saleInventory = new SaleInventory();
            saleInventory.setSaleInventoryId(new SaleInventoryId(sale.getId(), inventory.getId()));
            saleInventory.setSale(sale);
            saleInventory.setInventory(inventory);
            saleInventory.setQty(orderItem.getQty());
            saleInventory.setUnitprice(orderItem.getItem().getLastprice());
            saleInventory.setStatus(Status.ACTIVE.getValue());
            saleInventory.fillCompulsory(orderItemsList.getUserId());
            saleInventoryList.add(saleInventory);
        });
        sale.setSaleInventoryList(saleInventoryList);

        return saleRepository.save(sale);
    }

    @Override
    public Sale getSaleById(String saleId) {
        customValidator.validateNullOrEmpty(saleId, "saleId");
        Sale sale = saleRepository.findByIdAndStatus(Long.valueOf(saleId), Status.ACTIVE.getValue());
        customValidator.validateFoundNull(sale, "sale");
        return sale;
    }

    private Sale mapSaleDetails(Sale sale) {
        sale.setName(sale.getCustomer().getName());
        sale.setEmail(sale.getCustomer().getEmail());
        sale.setContact1(sale.getCustomer().getContact1());
        return sale;
    }
}
