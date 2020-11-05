package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.db.User;

public interface UserService {
    User registerUser(User user);

    CustomerUser saveCustomer(CustomerUser customerUser);

    void updateFailedAttempts(User user);
}
