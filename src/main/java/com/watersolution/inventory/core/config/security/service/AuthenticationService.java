package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationRequest;
import com.watersolution.inventory.core.config.security.jwt.model.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    AuthenticationResponse registerUser(User user);
}
