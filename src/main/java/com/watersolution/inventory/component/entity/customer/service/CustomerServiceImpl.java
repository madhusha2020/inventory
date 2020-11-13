package com.watersolution.inventory.component.entity.customer.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.customer.model.api.CustomerList;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public CustomerList getAllCustomers(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Customer> customers = customerRepository.findAllByStatusIn(Status.getAllStatusAsList(), PageRequest.of(page, pageDetails.getLimit()));
        return new CustomerList(customers.getContent(), customers.getTotalPages());
    }

    @Override
    public CustomerList getAllActiveCustomers(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Customer> customers = customerRepository.findAllByStatus(Status.ACTIVE.getValue(), PageRequest.of(page, pageDetails.getLimit()));
        return new CustomerList(customers.getContent(), customers.getTotalPages());
    }

    @Override
    public CustomerList searchCustomers(PageDetails pageDetails) {
        customValidator.validateFoundNull(pageDetails.getSearchFilter(), ErrorCodes.SEARCH_FILTER);
        customValidator.validateNullOrEmpty(pageDetails.getSearchFilter().getStatusList(), ErrorCodes.STATUS_LIST);
        Page<Customer> customers = searchCustomerQuery(pageDetails);
        return new CustomerList(customers.getContent(), customers.getTotalPages());
    }

    @Override
    public Customer getCustomerById(String id) {
        customValidator.validateNullOrEmpty(id, "id");
        return customerRepository.findByIdAndStatusIn(Long.valueOf(id), Status.getAllStatusAsList());
    }


    @Override
    public Customer getActiveCustomerById(long id) {
        return customerRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setStatus(Status.ACTIVE.getValue());
        customer.fillCompulsory(customer.getUserId());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.fillUpdateCompulsory(customer.getUserId());
        customer.setModifieddate(new Date());
        return customerRepository.save(customer);
    }

    private Page<Customer> searchCustomerQuery(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        if (pageDetails.getSearchFilter().getName() != null && pageDetails.getSearchFilter().getType() != null) {
            return customerRepository.findAllByNameLikeAndTypeLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getName() != null) {
            return customerRepository.findAllByNameLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getType() != null) {
            return customerRepository.findAllByTypeLikeAndStatusIn(
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else {
            return customerRepository.findAllByStatusIn(
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        }
    }
}
