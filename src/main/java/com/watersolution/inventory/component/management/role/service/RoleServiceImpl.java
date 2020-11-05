package com.watersolution.inventory.component.management.role.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.role.model.api.ModuleList;
import com.watersolution.inventory.component.management.role.model.api.RoleList;
import com.watersolution.inventory.component.management.role.model.db.Module;
import com.watersolution.inventory.component.management.role.model.db.PrivilegeId;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.repository.ModuleRepository;
import com.watersolution.inventory.component.management.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public RoleList getAllRoles() {
        return new RoleList(roleRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Override
    public ModuleList getAllModules() {
        List<Module> moduleList = moduleRepository.findAllByStatus(Status.ACTIVE.getValue());
        return new ModuleList(moduleList);
    }

    @Override
    public Role saveRole(Role role) {
        role.fillCompulsory(role.getUserId());
        role.setStatus(Status.ACTIVE.getValue());
        role.getPrivileges().stream().forEach(privilege -> {
            privilege.setRole(role);
            privilege.setPrivilegeId(new PrivilegeId(role.getName(), privilege.getModule().getId()));
            privilege.fillCompulsory(role.getUserId());
            privilege.setStatus(Status.ACTIVE.getValue());
        });
        return roleRepository.save(role);
    }
}
