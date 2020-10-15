package com.watersolution.inventory.component.management.role.repository;

import com.watersolution.inventory.component.management.role.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
