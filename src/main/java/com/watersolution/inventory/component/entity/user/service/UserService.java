package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.api.EmployeeUser;
import com.watersolution.inventory.component.entity.user.model.api.UserList;
import com.watersolution.inventory.component.entity.user.model.db.User;

public interface UserService {
    User registerUser(User user);

    UserList getAllUsers();

    UserList getAllActiveUsers();

    CustomerUser getCustomerById(String id);

    CustomerUser saveCustomer(CustomerUser customerUser);

    CustomerUser updateCustomer(CustomerUser customerUser);

    EmployeeUser getEmployeeById(String id);

    EmployeeUser saveEmployee(EmployeeUser employeeUser);

    EmployeeUser updateEmployee(EmployeeUser employeeUser);

    void updateFailedAttempts(User user);
}
