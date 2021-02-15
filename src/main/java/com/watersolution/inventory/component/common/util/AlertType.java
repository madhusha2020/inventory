package com.watersolution.inventory.component.common.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.Arrays;
import java.util.Collections;

public enum AlertType {

    ORDER_ALERT("Order Alert"),
    PURCHASE_ORDER_ALERT("Purchase Order Alert"),
    INVENTORY_ALERT("Inventory Alert"),
    DELIVERY_ALERT("Delivery Alert"),
    COMPLAIN_ALERT("Complain Alert");

    private final String value;

    AlertType(String value) {
        this.value = value;
    }

    public static AlertType fromValue(String type) {
        return Arrays.stream(AlertType.values())
                .filter(m -> m.getValue().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Description", Collections.singletonList("Alert Type")));
    }

    public String getValue() {
        return value;
    }
}
