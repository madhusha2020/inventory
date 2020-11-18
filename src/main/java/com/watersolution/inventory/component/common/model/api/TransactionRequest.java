package com.watersolution.inventory.component.common.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionRequest {
    private long id;

    public TransactionRequest(long id) {
        this.id = id;
    }
}
