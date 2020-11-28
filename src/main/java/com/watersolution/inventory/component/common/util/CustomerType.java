package com.watersolution.inventory.component.common.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.Arrays;
import java.util.Collections;

public enum CustomerType {

    EXTERNAL("EXTERNAL"),
    REGULAR("REGULAR");

    private final String value;

    CustomerType(String value) {
        this.value = value;
    }

    public static CustomerType fromValue(String type) {
        return Arrays.stream(CustomerType.values())
                .filter(m -> m.getValue().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Description", Collections.singletonList("Customer Type")));
    }

    public String getValue() {
        return value;
    }
}
