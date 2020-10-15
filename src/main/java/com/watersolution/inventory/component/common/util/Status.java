package com.watersolution.inventory.component.common.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.Arrays;
import java.util.Collections;

public enum Status {

    ACTIVE(1),
    INITIATED(2),
    DECLINED(3),
    EXPIRED(4),
    PENDING(5),
    RETRACTED(6),
    LOCKED(7),
    FAILURE(8),
    DELETED(9),
    SUSPENED(10);

    private Integer value;

    Status(int value) {
        this.value = value;
    }

    public static Status fromValue(int status) {
        return Arrays.stream(Status.values())
                .filter(m -> m.getValue() == status)
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Description", Collections.singletonList("Status")));
    }

    public Integer getValue() {
        return value;
    }
}
