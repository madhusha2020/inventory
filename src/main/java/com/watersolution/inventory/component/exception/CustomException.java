package com.watersolution.inventory.component.exception;

import java.util.List;
import java.util.Optional;

public class CustomException extends RuntimeException {

    private final String errorCode;
    private final String errorDesc;
    private final List<String> errorValues;

    public CustomException(String errCode, String errorDesc, List<String> errorValues) {
        super(errorDesc);
        this.errorCode = errCode;
        this.errorDesc = errorDesc;
        this.errorValues = errorValues;
    }

    public Optional<String> getErrorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public Optional<String> getErrorDescription() {
        return Optional.ofNullable(this.errorDesc);
    }

    public Optional<List<String>> getErrorValues() {
        return Optional.ofNullable(this.errorValues);
    }
}
