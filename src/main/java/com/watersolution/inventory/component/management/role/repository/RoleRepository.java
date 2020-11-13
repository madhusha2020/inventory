package com.watersolution.inventory.component.management.role.repository;

import com.watersolution.inventory.component.management.role.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByStatus(int status);

    List<Role> findAllByStatusIn(List<Integer> statusList);
}
