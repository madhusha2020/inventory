package com.watersolution.inventory.component.management.role.repository;

import com.watersolution.inventory.component.management.role.model.db.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findAllByStatus(int status);
}
