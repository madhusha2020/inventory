package com.watersolution.inventory.component.management.role.repository;

import com.watersolution.inventory.component.management.role.model.db.Privilege;
import com.watersolution.inventory.component.management.role.model.db.PrivilegeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, PrivilegeId> {

}
