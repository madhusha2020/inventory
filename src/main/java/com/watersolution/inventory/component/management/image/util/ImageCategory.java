package com.watersolution.inventory.component.management.image.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.Arrays;
import java.util.Collections;

public enum ImageCategory {

    EMPLOYEE("EMPLOYEE"),
    ITEM("ITEM");

    private final String value;

    ImageCategory(String value) {
        this.value = value;
    }

    public static ImageCategory fromValue(String category) {
        return Arrays.stream(ImageCategory.values())
                .filter(m -> m.getValue().equalsIgnoreCase(category))
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Category", Collections.singletonList("Category")));
    }

    public String getValue() {
        return value;
    }
}
