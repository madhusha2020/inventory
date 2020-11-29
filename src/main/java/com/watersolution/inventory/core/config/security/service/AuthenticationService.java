package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.db.User;

public interface AuthenticationService {

    User login(User user);

    User logout(User user);

    CustomerUser registerUser(CustomerUser customerUser);
}
