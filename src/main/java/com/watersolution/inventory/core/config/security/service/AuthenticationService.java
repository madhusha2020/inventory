package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.entity.user.model.User;

public interface AuthenticationService {

    User login(User user);

    User registerUser(User user);
}
