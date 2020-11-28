package com.watersolution.inventory.component.common.util;

import com.watersolution.inventory.component.exception.CustomException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Status {

    ACTIVE(1),
    AWAITING(2),
    REJECTED(3),
    EXPIRED(4),
    PENDING(5),
    RETRACTED(6),
    LOCKED(7),
    FAILURE(8),
    DELETED(9),
    SUSPENDED(10);

    private final Integer value;

    Status(int value) {
        this.value = value;
    }

    public static Status fromValue(int status) {
        return Arrays.stream(Status.values())
                .filter(m -> m.getValue() == status)
                .findAny()
                .orElseThrow(() -> new CustomException("INVALID", "Invalid Description", Collections.singletonList("Status")));
    }

    public static List<Integer> getAllStatusAsList() {
        List<Integer> statusList = new ArrayList<>();
        Arrays.stream(Status.values())
                .forEach(statusValue -> {
                    statusList.add(statusValue.value);
                });
        return statusList;
    }

    public static void validateState(String entity, int statusValue, Status statusEnumValue) {
        String message = "{0} is not in {1} state".replace("{0}", entity);
        switch (statusEnumValue) {
            case ACTIVE:
                if (Status.ACTIVE.getValue() != statusValue) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, message.replace("{1}", Status.ACTIVE.name().toLowerCase()), Collections.singletonList(message));
                }
                break;
            case PENDING:
                if (Status.PENDING.getValue() != statusValue) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, message.replace("{1}", Status.PENDING.name().toLowerCase()), Collections.singletonList(message));
                }
                break;
            case AWAITING:
                if (Status.AWAITING.getValue() != statusValue) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, message.replace("{1}", Status.AWAITING.name().toLowerCase()), Collections.singletonList(message));
                }
                break;
            case REJECTED:
                if (Status.REJECTED.getValue() != statusValue) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, message.replace("{1}", Status.REJECTED.name().toLowerCase()), Collections.singletonList(message));
                }
                break;
            case SUSPENDED:
                if (Status.SUSPENDED.getValue() != statusValue) {
                    throw new CustomException(ErrorCodes.BAD_REQUEST, message.replace("{1}", Status.SUSPENDED.name().toLowerCase()), Collections.singletonList(message));
                }
                break;
            default:
                throw new CustomException(ErrorCodes.BAD_REQUEST, "State is undefined", Collections.singletonList("State is undefined"));
        }
    }

    public Integer getValue() {
        return value;
    }
}
