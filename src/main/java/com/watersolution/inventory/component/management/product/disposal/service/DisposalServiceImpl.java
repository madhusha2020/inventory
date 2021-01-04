package com.watersolution.inventory.component.management.product.disposal.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.inventory.service.InventoryService;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalInventoryList;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalList;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventoryId;
import com.watersolution.inventory.component.management.product.disposal.repository.DisposalInventoryRepository;
import com.watersolution.inventory.component.management.product.disposal.repository.DisposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class DisposalServiceImpl implements DisposalService {

    @Autowired
    private DisposalRepository disposalRepository;
    @Autowired
    private DisposalInventoryRepository disposalInventoryRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public DisposalList getAllDisposalProducts() {
        return new DisposalList(disposalRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapDisposalDetails).collect(Collectors.toList()));
    }

    @Override
    public Disposal getDisposalById(String disposalId) {
        customValidator.validateNullOrEmpty(disposalId, "disposalId");
        return mapDisposalDetails(disposalRepository.findByIdAndStatusIn(Long.valueOf(disposalId), Status.getAllStatusAsList()));
    }

    @Transactional
    @Override
    public DisposalInventoryList createDisposalProduct(DisposalInventoryList disposalInventoryList) {

        /**
         * Pre Validate Disposal
         */
        inventoryService.preDisposalValidate(disposalInventoryList.getDisposalInventoryList());

        disposalInventoryList.getDisposal().setStatus(Status.PENDING.getValue());
        disposalInventoryList.getDisposal().fillCompulsory(disposalInventoryList.getDisposal().getUserId());

        Disposal disposal = disposalRepository.save(disposalInventoryList.getDisposal());

        disposalInventoryList.getDisposalInventoryList().stream().forEach(disposalInventory -> {
            disposalInventory.setDisposal(disposal);
            disposalInventory.setDisposalInventoryId(new DisposalInventoryId(disposal.getId(), disposalInventory.getInventory().getId()));
            disposalInventory.setStatus(Status.ACTIVE.getValue());
            disposalInventory.fillCompulsory(disposal.getUserId());
        });

        /**
         * Inventory Update
         * Disposal Init
         */
        inventoryService.disposalUpdateInventory(disposalInventoryList.getDisposalInventoryList());
        disposalInventoryRepository.saveAll(disposalInventoryList.getDisposalInventoryList());

        return disposalInventoryList;
    }


    @Override
    public Disposal approveDisposal(TransactionRequest transactionRequest) {
        customValidator.validateFoundNull(transactionRequest.getId(), "disposalId");

        Disposal disposal = disposalRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        disposal.setDate(LocalDate.now());
        disposal.setStatus(Status.ACTIVE.getValue());
        disposal.fillUpdateCompulsory(transactionRequest.getUserId());

        disposalRepository.save(disposal);
        return disposal;
    }

    @Override
    public Disposal suspendDisposal(TransactionRequest transactionRequest) {
        customValidator.validateFoundNull(transactionRequest.getId(), "disposalId");

        Disposal disposal = disposalRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        disposal.setDate(LocalDate.now());
        disposal.setStatus(Status.SUSPENDED.getValue());
        disposal.fillUpdateCompulsory(transactionRequest.getUserId());

        disposalRepository.save(disposal);
        return disposal;
    }

    private Disposal mapDisposalDetails(Disposal disposal) {
        return disposal;
    }
}
