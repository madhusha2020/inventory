package com.watersolution.inventory.component.entity.user.util;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.component.entity.user.repository.UserRepository;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import com.watersolution.inventory.component.management.role.model.role.UserRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceHelperImpl implements UserServiceHelper {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUserWithRoles(User user, List<String> roles) {
        validateUserName(user);
        validateUserRoles(roles);

        user.setStatus(Status.ACTIVE.getValue());
        user.fillCompulsory(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFailedAttempts(0);

        List<UserRole> userRoleList = new ArrayList<>();
        roles.stream().forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserRoleId(new UserRoleId(user.getUserName(), role));
            userRole.fillCompulsory(user.getUserId());
            userRole.setUser(user);
            userRole.setRole(new Role(role));
            userRoleList.add(userRole);
        });
        user.setUserRoles(userRoleList);

        return userRepository.save(user);
    }

    @Override
    public User updateUserWithRoles(User user, List<String> roles) {
        validateUserRoles(roles);
        validatePassword(user);

        user.fillUpdateCompulsory(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFailedAttempts(0);

        List<UserRole> userRoleList = new ArrayList<>();
        roles.stream().forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserRoleId(new UserRoleId(user.getUserName(), role));
            userRole.fillCompulsory(user.getUserId());
            userRole.setUser(user);
            userRole.setRole(new Role(role));
            userRoleList.add(userRole);
        });
        user.setUserRoles(userRoleList);

        return userRepository.save(user);
    }

    @Override
    public void validateUserName(User user) {
        if (getActiveUserByUserName(user).isPresent()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "This Email is already taken", Collections.singletonList("This Email is already taken"));
        }
    }

    @Override
    public void validateUserRoles(List<String> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Please select at least one user role", Collections.singletonList("Please select at least one user role"));
        }
    }

    @Override
    public void validatePassword(User user) {
        Optional<User> dbUser = userRepository.findByUserNameAndStatus(user.getUserName(), Status.ACTIVE.getValue());
        if (dbUser.isPresent()) {
            if (user.getOldPassword() != null) {
                if (!passwordEncoder.matches(user.getOldPassword(), dbUser.get().getPassword())) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, "Your old password is wrong", Collections.singletonList("Your old password is wrong"));
                }
            }
        } else {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid or inactive user", Collections.singletonList("Invalid or inactive user"));
        }
    }

    @Override
    public Optional<User> getActiveUserByUserName(User user) {
        return userRepository.findByUserNameAndStatus(user.getUserName(), Status.ACTIVE.getValue());
    }
}
