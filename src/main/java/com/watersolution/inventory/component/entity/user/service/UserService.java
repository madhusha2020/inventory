package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.api.EmployeeUser;
import com.watersolution.inventory.component.entity.user.model.api.UserList;
import com.watersolution.inventory.component.entity.user.model.db.User;

public interface UserService {
    User registerUser(User user);

    UserList getAllUsers();

    CustomerUser saveCustomer(CustomerUser customerUser);

    EmployeeUser saveEmployee(EmployeeUser employeeUser);

    void updateFailedAttempts(User user);
}
