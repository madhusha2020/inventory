package com.watersolution.inventory.component.entity.user.util;

import com.watersolution.inventory.component.entity.user.model.db.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceHelper {
    User saveUserWithRoles(User user, List<String> roles);

    User updateUserWithRoles(User user, List<String> roles);

    void validateUserName(User user);

    void validateUserRoles(List<String> roles);

    void validatePassword(User user);

    Optional<User> getActiveUserByUserName(User user);
}
