package com.watersolution.inventory.component.management.role.service;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.role.model.api.ModuleList;
import com.watersolution.inventory.component.management.role.model.api.RoleList;
import com.watersolution.inventory.component.management.role.model.db.Module;
import com.watersolution.inventory.component.management.role.model.db.Privilege;
import com.watersolution.inventory.component.management.role.model.db.PrivilegeId;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.repository.ModuleRepository;
import com.watersolution.inventory.component.management.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public RoleList getAllRoles() {
        return new RoleList(roleRepository.findAllByStatusIn(Status.getAllStatusAsList()));
    }

    @Override
    public RoleList getAllActiveRoles() {
        return new RoleList(roleRepository.findAllByStatus(Status.ACTIVE.getValue()));
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByNameAndStatusIn(roleName, Status.getAllStatusAsList());
    }

    @Override
    public ModuleList getAllModules() {
        List<Module> moduleList = moduleRepository.findAllByStatus(Status.ACTIVE.getValue());
        return new ModuleList(moduleList);
    }

    @Transactional
    @Override
    public Role saveRole(Role role) {
        validatePermissions(role);
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

    @Transactional
    @Override
    public Role updateRole(Role role) {
        validatePermissions(role);

        Role savedRole = roleRepository.findByNameAndStatusIn(role.getName(), Status.getAllStatusAsList());

        Map<Long, Privilege> privilegeMap = new HashMap<>();
        List<Privilege> privilegeList = new ArrayList<>();

        savedRole.getPrivileges().stream().forEach(privilege -> {
            privilege.setRole(savedRole);
            privilege.setPrivilegeId(new PrivilegeId(savedRole.getName(), privilege.getModule().getId()));
            privilege.fillUpdateCompulsory(role.getUserId());
            privilege.setStatus(Status.DELETED.getValue());
            privilegeMap.put(privilege.getPrivilegeId().getModuleId(), privilege);
        });

        role.getPrivileges().stream().forEach(privilege -> {
            privilege.setRole(role);
            privilege.setPrivilegeId(new PrivilegeId(role.getName(), privilege.getModule().getId()));
            privilege.fillCompulsory(role.getUserId());
            privilege.setStatus(Status.ACTIVE.getValue());
            privilegeMap.put(privilege.getPrivilegeId().getModuleId(), privilege);
        });
        privilegeMap.entrySet().stream().forEach(privilege -> {
            privilegeList.add(privilege.getValue());
        });
        role.fillCompulsory(role.getUserId());
        role.setStatus(Status.ACTIVE.getValue());
        role.setPrivileges(privilegeList);

        return roleRepository.save(role);
    }

    private void validatePermissions(Role role) {
        if (role.getPrivileges() == null || role.getPrivileges().isEmpty()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Please select at least one user role", Collections.singletonList("Please select at least one user role"));
        }
    }
}
