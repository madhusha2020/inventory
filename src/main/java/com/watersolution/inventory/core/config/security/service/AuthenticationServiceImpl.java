package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.component.entity.user.service.UserService;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.core.config.security.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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

    @Override
    public User login(User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        } catch (BadCredentialsException e) {
            userService.updateFailedAttempts(user);
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Incorrect username or password", Collections.singletonList("Incorrect username or password"));
        }
        catch (LockedException e){
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Exceeds the maximum number of retries for Login Password", Collections.singletonList("Incorrect username or password"));
        }
        catch (DisabledException e){
            throw new CustomException(ErrorCodes.UNAUTHORIZED, "Inactive User", Collections.singletonList("Incorrect username or password"));
        }
        final UserDetails userDetails = inventoryUserDetailsService.loadUserByUsername(user.getUserName());
        user.setToken(jwtUtil.generateToken(userDetails));
        user.setPassword(null);
        return user;
    }

    @Override
    public User registerUser(User user) {
        userService.registerUser(user);
        return user;
    }
}
