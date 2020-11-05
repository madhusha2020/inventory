package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.component.entity.user.repository.UserRepository;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import com.watersolution.inventory.component.management.role.model.role.UserRoleId;
import com.watersolution.inventory.component.management.role.util.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        validateUserName(user);
        user.setStatus(Status.ACTIVE.getValue());
        user.fillCompulsory(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(new UserRoleId(user.getUserName(), Roles.ROLE_CUSTOMER.getValue()));
        userRole.setUser(user);
        userRole.setRole(new Role(Roles.ROLE_CUSTOMER.getValue()));

        user.setUserRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public CustomerUser saveCustomer(CustomerUser customerUser) {
        customerUser.setUser(saveUserWithRoles(customerUser.getUser(), customerUser.getRoleNameList()));
        customerUser.setCustomer(customerService.saveCustomer(customerUser.getCustomer()));
        return customerUser;
    }

    @Override
    public void updateFailedAttempts(User user) {
        Optional<User> updatableUser = getActiveUserByUserName(user);
        if (updatableUser.isPresent()) {
            updatableUser.get().setFailedAttempts(updatableUser.get().getFailedAttempts() + 1);
            userRepository.save(updatableUser.get());
        }
    }

    private User saveUserWithRoles(User user, List<String> roles){
        validateUserName(user);
        validateUserRoles(roles);
        user.setStatus(Status.ACTIVE.getValue());
        user.fillCompulsory(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<UserRole> userRoleList = new ArrayList<>();
        roles.stream().forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserRoleId(new UserRoleId(user.getUserName(), role));
            userRole.setUser(user);
            userRole.setRole(new Role(role));
            userRoleList.add(userRole);
        });
        user.setUserRoles(userRoleList);

        return userRepository.save(user);
    }

    private void validateUserName(User user) {
        if (getActiveUserByUserName(user).isPresent()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "This Email is already taken", Collections.singletonList("This Email is already taken"));
        }
    }

    private void validateUserRoles(List<String> roles) {
        if (roles == null || roles.isEmpty()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Please select at least one user role", Collections.singletonList("Please select at least one user role"));
        }
    }

    private Optional<User> getActiveUserByUserName(User user) {
        return userRepository.findByUserNameAndStatus(user.getUserName(), Status.ACTIVE.getValue());
    }
}
