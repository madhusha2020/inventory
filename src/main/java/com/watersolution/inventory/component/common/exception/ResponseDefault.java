package com.watersolution.inventory.component.common.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDefault {
    private String responseCode;
    private String responseDescription;
    private List<String> responseValues;

    public ResponseDefault addResponseValuesItem(String responseValuesItem) {
        if (this.responseValues == null) {
            this.responseValues = new ArrayList<>();
        }
        this.responseValues.add(responseValuesItem);
        return this;
    }
}
