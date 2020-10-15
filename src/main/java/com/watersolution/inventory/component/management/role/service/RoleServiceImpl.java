package com.watersolution.inventory.component.management.role.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.role.model.api.PrivilegeList;
import com.watersolution.inventory.component.management.role.model.privilege.Privilege;
import com.watersolution.inventory.component.management.role.model.privilege.PrivilegeId;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.repository.PrivilegeRepository;
import com.watersolution.inventory.component.management.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PrivilegeList getAllPrivileges(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Privilege> privileges = privilegeRepository.findAllByStatus(Status.ACTIVE.getValue(), PageRequest.of(page, pageDetails.getLimit()));
        return new PrivilegeList(privileges.getContent(), privileges.getTotalPages());
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
