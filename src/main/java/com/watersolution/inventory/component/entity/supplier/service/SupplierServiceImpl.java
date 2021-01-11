package com.watersolution.inventory.component.entity.supplier.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.supplier.model.api.SupplierList;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.entity.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierList getAllSuppliers() {
        return new SupplierList(supplierRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapSupplierDetails).collect(Collectors.toList()));
    }

    @Override
    public SupplierList getAllActiveSuppliers() {
        return new SupplierList(supplierRepository.findAllByStatus(Status.ACTIVE.getValue()).stream().map(this::mapSupplierDetails).collect(Collectors.toList()));
    }

    @Override
    public Supplier getSupplier(String id) {
        return mapSupplierDetails(supplierRepository.findByIdAndStatusIn(Long.valueOf(id), Status.getAllStatusAsList()));
    }

    @Override
    public Supplier getActiveSupplierById(long id) {
        return mapSupplierDetails(supplierRepository.findByIdAndStatus(id, Status.ACTIVE.getValue()));
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        supplier.setStatus(Status.ACTIVE.getValue());
        supplier.fillCompulsory(supplier.getUserId());
        return mapSupplierDetails(supplierRepository.save(supplier));
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        supplier.fillUpdateCompulsory(supplier.getUserId());
        return mapSupplierDetails(supplierRepository.save(supplier));
    }

    @Override
    public Supplier suspendSupplier(TransactionRequest transactionRequest) {
        Supplier supplier = supplierRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        supplier.setStatus(Status.SUSPENDED.getValue());
        supplier.fillUpdateCompulsory(transactionRequest.getUserId());
        return mapSupplierDetails(supplierRepository.save(supplier));
    }

    @Override
    public Supplier activateSupplier(TransactionRequest transactionRequest) {
        Supplier supplier = supplierRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        supplier.setStatus(Status.ACTIVE.getValue());
        supplier.fillUpdateCompulsory(transactionRequest.getUserId());
        return mapSupplierDetails(supplierRepository.save(supplier));
    }

    private Supplier mapSupplierDetails(Supplier supplier) {
        return supplier;
    }
}
