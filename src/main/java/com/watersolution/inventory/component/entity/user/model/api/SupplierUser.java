package com.watersolution.inventory.component.entity.user.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.entity.user.model.db.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierUser extends ResponseDefault {
    @Valid
    private User user;
    @Valid
    private Supplier supplier;
    private List<String> roleNameList;

    public SupplierUser() {
    }

    public SupplierUser(@Valid User user, @Valid Supplier supplier, List<String> roleNameList) {
        this.user = user;
        this.supplier = supplier;
        this.roleNameList = roleNameList;
    }
}
