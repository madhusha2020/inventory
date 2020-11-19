package com.watersolution.inventory.component.common.model.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionRequest {
    private long id;
    private String email;

    public TransactionRequest() {
    }

    public TransactionRequest(long id) {
        this.id = id;
    }

    public TransactionRequest(String email) {
        this.email = email;
    }

    public TransactionRequest(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
