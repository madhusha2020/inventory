package com.watersolution.inventory.component.entity.discount.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionDetails extends ResponseDefault {

    private String category;
    private String userName;
    private String type;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal totalWithDiscount;

    public TransactionDetails() {
    }
}
