package com.watersolution.inventory.component.entity.discount.service;

import com.watersolution.inventory.component.entity.discount.model.api.TransactionDetails;
import com.watersolution.inventory.component.entity.discount.model.db.Discount;

public interface DiscountService {

    Discount getDiscount(String category, String type);

    TransactionDetails getTransactionDetails(TransactionDetails transactionDetails);
}
