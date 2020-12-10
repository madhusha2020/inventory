package com.watersolution.inventory.component.entity.discount.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.entity.discount.model.api.TransactionDetails;
import com.watersolution.inventory.component.entity.discount.model.db.Discount;
import com.watersolution.inventory.component.entity.discount.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private CustomerService customerService;

    @Override
    public Discount getDiscount(String category, String type) {
        return discountRepository.findByCategoryAndTypeAndStatus(category.toUpperCase(), type.toUpperCase(), Status.ACTIVE.getValue());
    }

    @Override
    public TransactionDetails getTransactionDetails(TransactionDetails transactionDetails) {

        switch (transactionDetails.getCategory().toUpperCase()){
            case "CUSTOMER" :
                transactionDetails.setType(customerService.getCustomerByUserName(transactionDetails.getUserName()).getType());
                break;
        }

        Discount discount = discountRepository.findByCategoryAndTypeAndStatus(transactionDetails.getCategory().toUpperCase(), transactionDetails.getType().toUpperCase(), Status.ACTIVE.getValue());
        transactionDetails.setDiscount(transactionDetails.getTotal().multiply(discount.getDiscount()));
        transactionDetails.setTotalWithDiscount(transactionDetails.getTotal().subtract(transactionDetails.getTotal().multiply(discount.getDiscount())));
        return transactionDetails;
    }
}

