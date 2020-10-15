package com.watersolution.inventory.component.management.role.repository;

import com.watersolution.inventory.component.management.role.model.privilege.Privilege;
import com.watersolution.inventory.component.management.role.model.privilege.PrivilegeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, PrivilegeId> {

    Page<Privilege> findAllByStatus(int status, Pageable pageable);
}
