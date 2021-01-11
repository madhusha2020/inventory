package com.watersolution.inventory.component.management.payment.customer.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerPaymentList extends ResponseDefault {

    @Valid
    private List<CustomerPayment> customerPaymentList;

    public CustomerPaymentList(@Valid List<CustomerPayment> customerPaymentList) {
        this.customerPaymentList = customerPaymentList;
    }
}
