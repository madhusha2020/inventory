package com.watersolution.inventory.component.entity.customer.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.entity.customer.model.api.CustomerList;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;

public interface CustomerService {

    CustomerList getAllCustomers(PageDetails pageDetails);

    CustomerList getAllActiveCustomers(PageDetails pageDetails);

    CustomerList searchCustomers(PageDetails pageDetails);

    Customer getCustomerById(String id);

    Customer getActiveCustomerById(long id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerByUserName(String userName);
}
