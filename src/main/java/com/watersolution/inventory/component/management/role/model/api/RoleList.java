package com.watersolution.inventory.component.management.role.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.role.model.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleList extends ResponseDefault {
    @Valid
    private List<Role> roles;

    public RoleList(List<Role> roles) {
        this.roles = roles;
    }
}
