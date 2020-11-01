package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.entity.user.model.db.User;

public interface UserService {
    User registerUser(User user);

    User saveUser(User user);

    void updateFailedAttempts(User user);
}
