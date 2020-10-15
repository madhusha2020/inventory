package com.watersolution.inventory.component.management.role.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.management.role.model.api.PrivilegeList;
import com.watersolution.inventory.component.management.role.model.role.Role;

public interface RoleService {
    PrivilegeList getAllPrivileges(PageDetails pageDetails);

    Role saveRole(Role role);
}
