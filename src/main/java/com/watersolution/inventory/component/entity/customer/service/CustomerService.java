package com.watersolution.inventory.component.entity.customer.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.entity.customer.model.api.CustomerList;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;

public interface CustomerService {

    CustomerList getAllCustomers(PageDetails pageDetails);

    CustomerList searchCustomers(PageDetails pageDetails);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomer(long id);
}
