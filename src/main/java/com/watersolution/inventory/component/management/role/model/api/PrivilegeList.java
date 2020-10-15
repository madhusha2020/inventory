package com.watersolution.inventory.component.management.role.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.role.model.privilege.Privilege;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PrivilegeList extends ResponseDefault {
    private List<Privilege> privileges;
    private int totalPages;

    public PrivilegeList(List<Privilege> privileges, int totalPages) {
        this.privileges = privileges;
        this.totalPages = totalPages;
    }
}
