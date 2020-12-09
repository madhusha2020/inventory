package com.watersolution.inventory.component.management.role.service;

import com.watersolution.inventory.component.management.role.model.api.ModuleList;
import com.watersolution.inventory.component.management.role.model.api.RoleList;
import com.watersolution.inventory.component.management.role.model.role.Role;

public interface RoleService {
    RoleList getAllRoles();

    RoleList getAllActiveRoles();

    Role getRoleByRoleName(String roleName);

    ModuleList getAllModules();

    Role saveRole(Role role);

    Role updateRole(Role role);
}
