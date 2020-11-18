package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.entity.user.model.api.CustomerUser;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.component.entity.user.service.UserService;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.core.config.security.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private InventoryUserDetailsService inventoryUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @Transactional
    @Override
    public User login(User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        } catch (BadCredentialsException e) {
            userService.updateFailedAttempts(user);
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Incorrect username or password", Collections.singletonList("Incorrect username or password"));
        } catch (LockedException e) {
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Exceeds the maximum number of retries for Login Password", Collections.singletonList("Incorrect username or password"));
        } catch (DisabledException e) {
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Inactive User", Collections.singletonList("Incorrect username or password"));
        }
        userService.resetFailedAttempts(user);
        user.setToken(jwtUtil.generateToken(inventoryUserDetailsService.loadUserByUsername(user.getUserName())));
        user.setPassword(null);
        return user;
    }

    @Transactional
    @Override
    public CustomerUser registerUser(CustomerUser customerUser) {
        customerUser.setUser(userService.registerUser(customerUser.getUser()));
        customerUser.setCustomer(customerService.saveCustomer(customerUser.getCustomer()));
        customerUser.getUser().setToken(jwtUtil.generateToken(inventoryUserDetailsService.loadUserByUsername(customerUser.getUser().getUserName())));
        return customerUser;
    }
}
