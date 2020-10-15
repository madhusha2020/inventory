package com.watersolution.inventory.core.config.security.jwt.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationRequest() {
    }
}
