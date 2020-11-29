package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.entity.user.model.db.User;

public interface TokenService {

    void saveToken(User user);

    void deleteToken(User user);
}
