package com.watersolution.inventory.component.entity.user.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.user.model.db.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerUser extends ResponseDefault {
    @Valid
    private User user;
    @Valid
    private Customer customer;
    private List<String> roleNameList;
}
