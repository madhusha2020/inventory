package com.watersolution.inventory.component.entity.customer.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerList extends ResponseDefault {
    @Valid
    private List<Customer> customers;
    private int totalPages;

    public CustomerList(List<Customer> customers, int totalPages) {
        this.customers = customers;
        this.totalPages = totalPages;
    }
}
