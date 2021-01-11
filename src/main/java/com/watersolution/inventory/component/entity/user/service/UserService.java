package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.user.model.api.*;
import com.watersolution.inventory.component.entity.user.model.db.User;

public interface UserService {
    User registerUser(User user);

    UserList getAllUsers();

    UserList getAllActiveUsers();

    UserWithUserRoles getUserByUserName(String userName);

    User activateUser(TransactionRequest transactionRequest);

    User suspendUser(TransactionRequest transactionRequest);

    CustomerUser getCustomerById(String id);

    CustomerUser saveCustomer(CustomerUser customerUser);

    CustomerUser updateCustomer(CustomerUser customerUser);

    EmployeeUser getEmployeeById(String id);

    EmployeeUser saveEmployee(EmployeeUser employeeUser);

    EmployeeUser updateEmployee(EmployeeUser employeeUser);

    SupplierUser getSupplierById(String id);

    SupplierUser saveSupplier(SupplierUser supplierUser);

    SupplierUser updateSupplier(SupplierUser supplierUser);

    void updateFailedAttempts(User user);

    void resetFailedAttempts(User user);
}
