package com.watersolution.inventory.component.management.role.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.Arrays;
import java.util.Collections;

public enum Roles {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public static Roles fromValue(String role) {
        return Arrays.stream(Roles.values())
                .filter(m -> m.getValue().equals(role))
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Description", Collections.singletonList("Roles")));
    }

    public String getValue() {
        return value;
    }
}
