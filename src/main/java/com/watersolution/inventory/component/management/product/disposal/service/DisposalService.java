package com.watersolution.inventory.component.management.product.disposal.service;

import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalInventoryList;
import com.watersolution.inventory.component.management.product.disposal.model.api.DisposalList;

public interface DisposalService {

    DisposalList getAllDisposalProducts();

    DisposalInventoryList preDisposalValidate(DisposalInventoryList disposalInventoryList);

    DisposalInventoryList createDisposalProduct(DisposalInventoryList disposalInventoryList);
}
