package com.watersolution.inventory.component.management.product.disposal.service;

import com.watersolution.inventory.component.common.util.Status;
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

    @Override
    public DisposalList getAllDisposalProducts() {
        return new DisposalList(disposalRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapDisposalDetails).collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public DisposalInventoryList preDisposalValidate(DisposalInventoryList disposalInventoryList) {
        inventoryService.preDisposalValidate(disposalInventoryList.getDisposalInventoryList());
        return disposalInventoryList;
    }

    @Transactional
    @Override
    public DisposalInventoryList createDisposalProduct(DisposalInventoryList disposalInventoryList) {

        disposalInventoryList.getDisposal().setDate(LocalDate.now());
        disposalInventoryList.getDisposal().setStatus(Status.ACTIVE.getValue());
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

    private Disposal mapDisposalDetails(Disposal disposal) {
        return disposal;
    }
}
