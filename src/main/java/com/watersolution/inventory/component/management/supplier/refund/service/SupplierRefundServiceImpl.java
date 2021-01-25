package com.watersolution.inventory.component.management.supplier.refund.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.supplier.refund.model.api.SupplierRefundList;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventory;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefundInventoryId;
import com.watersolution.inventory.component.management.supplier.refund.repository.SupplierRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierRefundServiceImpl implements SupplierRefundService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SupplierRefundRepository supplierRefundRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public List<SupplierRefundInventory> saveAllSupplierRefundInventories(List<SupplierRefundInventory> supplierRefundInventoryList, Purchase purchase) {

        SupplierRefund supplierRefund = new SupplierRefund();
        supplierRefund.setCode(purchase.getCode());
        supplierRefund.setDescription("Purchase refunds");
        supplierRefund.setDate(LocalDate.now());
        supplierRefund.setReason("Rejected from GRN");
        supplierRefund.setPaymenttype("cheque");
        supplierRefund.setChequeno(purchase.getSupplierPayment().getChequeno());
        supplierRefund.setChequebank(purchase.getSupplierPayment().getChequebank());
        supplierRefund.setChequebranch(purchase.getSupplierPayment().getChequebranch());
        supplierRefund.setChequedate(purchase.getSupplierPayment().getChequedate());
        supplierRefund.setStatus(Status.ACTIVE.getValue());
        supplierRefund.setAmount(BigDecimal.ZERO);
        supplierRefund.fillCompulsory(purchase.getCreatedby());

        supplierRefundRepository.save(supplierRefund);

        supplierRefundInventoryList.stream().forEach(supplierRefundInventory -> {
            Inventory inventory = inventoryService.getByItemId(supplierRefundInventory.getItemId());
            customValidator.validateFoundNull(inventory, "inventory");
            supplierRefundInventory.setSupplierRefundInventoryId(new SupplierRefundInventoryId(supplierRefund.getId(), supplierRefundInventory.getItemId()));
            supplierRefundInventory.setSupplierRefund(supplierRefund);
            supplierRefundInventory.setInventory(inventory);
            supplierRefund.setAmount(supplierRefund.getAmount().add(new BigDecimal(supplierRefundInventory.getUnitprice() * supplierRefundInventory.getQty())));
        });
        supplierRefund.setSupplierRefundInventories(supplierRefundInventoryList);
        supplierRefundRepository.save(supplierRefund);

        return supplierRefundInventoryList;
    }

    @Override
    public SupplierRefundList getAllSupplierRefunds() {
        return new SupplierRefundList(supplierRefundRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapSupplierRefundDetails).collect(Collectors.toList()));
    }

    @Override
    public SupplierRefund getSupplierRefundById(String supplierRefundId) {
        return mapSupplierRefundDetails(supplierRefundRepository.findByIdAndStatusIn(Long.valueOf(supplierRefundId), Status.getAllStatusAsList()));
    }

    @Override
    public SupplierRefund updateSupplierRefund(SupplierRefund supplierRefund) {
        supplierRefund.fillUpdateCompulsory(supplierRefund.getUserId());
        return mapSupplierRefundDetails(supplierRefundRepository.save(supplierRefund));
    }

    private SupplierRefund mapSupplierRefundDetails(SupplierRefund supplierRefund) {
        return supplierRefund;
    }
}
