package com.watersolution.inventory.component.management.supplier.returns.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.supplier.returns.model.api.SupplierReturnList;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventory;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturnInventoryId;
import com.watersolution.inventory.component.management.supplier.returns.repository.SupplierReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierReturnServiceImpl implements SupplierReturnService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SupplierReturnRepository supplierReturnRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public List<SupplierReturnInventory> saveAllSupplierReturnInventories(List<SupplierReturnInventory> supplierReturnInventoryList, Purchase purchase) {

        SupplierReturn supplierReturn = new SupplierReturn();
        supplierReturn.setCode(purchase.getCode());
        supplierReturn.setReason("Rejected from GRN");
        supplierReturn.setSupplier(purchase.getSupplier());
        supplierReturn.setDorecived(purchase.getDate());
        supplierReturn.setStatus(Status.ACTIVE.getValue());
        supplierReturn.fillCompulsory(purchase.getCreatedby());

        supplierReturnRepository.save(supplierReturn);

        supplierReturnInventoryList.stream().forEach(supplierReturnInventory -> {
            Inventory inventory = inventoryService.getByItemId(supplierReturnInventory.getItemId());
            customValidator.validateFoundNull(inventory, "inventory");
            supplierReturnInventory.setSupplierReturnInventoryId(new SupplierReturnInventoryId(supplierReturn.getId(), supplierReturnInventory.getItemId()));
            supplierReturnInventory.setSupplierReturn(supplierReturn);
            supplierReturnInventory.setInventory(inventory);
        });
        supplierReturn.setSupplierReturnInventories(supplierReturnInventoryList);
        supplierReturnRepository.save(supplierReturn);

        return supplierReturnInventoryList;
    }

    @Override
    public SupplierReturnList getAllSupplierReturns() {
        return new SupplierReturnList(supplierReturnRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapSupplierReturnDetails).collect(Collectors.toList()));
    }

    @Override
    public SupplierReturn getSupplierReturnById(String supplierReturnId) {
        return mapSupplierReturnDetails(supplierReturnRepository.findByIdAndStatusIn(Long.valueOf(supplierReturnId), Status.getAllStatusAsList()));
    }

    @Override
    public SupplierReturn updateSupplierReturn(SupplierReturn supplierReturn) {
        supplierReturn.fillUpdateCompulsory(supplierReturn.getUserId());
        return mapSupplierReturnDetails(supplierReturnRepository.save(supplierReturn));
    }

    private SupplierReturn mapSupplierReturnDetails(SupplierReturn supplierReturn) {
        return supplierReturn;
    }
}
