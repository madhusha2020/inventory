package com.watersolution.inventory.component.management.product.disposal.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalInventoryList;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalList;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;

public interface DisposalService {

    DisposalList getAllDisposalProducts();

    Disposal getDisposalById(String disposalId);

    DisposalInventoryList createDisposalProduct(DisposalInventoryList disposalInventoryList);

    Disposal approveDisposal(TransactionRequest transactionRequest);

    Disposal suspendDisposal(TransactionRequest transactionRequest);
}
