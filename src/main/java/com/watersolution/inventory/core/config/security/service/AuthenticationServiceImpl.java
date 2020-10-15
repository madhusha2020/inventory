package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.component.entity.user.service.UserService;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationRequest;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationResponse;
import com.watersolution.inventory.core.config.security.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new CustomException(ErrorCodes.BAD_CREDENTIALS, "Incorrect username or password", Collections.singletonList("Incorrect username or password"));
        }
        final UserDetails userDetails = inventoryUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse registerUser(User user) {
        userService.registerUser(user);
        return new AuthenticationResponse();
    }
}
