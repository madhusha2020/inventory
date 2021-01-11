package com.watersolution.inventory.component.entity.supplier.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.supplier.model.api.SupplierList;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;

public interface SupplierService {

    SupplierList getAllSuppliers();

    SupplierList getAllActiveSuppliers();

    Supplier getSupplier(String id);

    Supplier getActiveSupplierById(long id);

    Supplier saveSupplier(Supplier supplier);

    Supplier updateSupplier(Supplier supplier);

    Supplier suspendSupplier(TransactionRequest transactionRequest);

    Supplier activateSupplier(TransactionRequest transactionRequest);
}
