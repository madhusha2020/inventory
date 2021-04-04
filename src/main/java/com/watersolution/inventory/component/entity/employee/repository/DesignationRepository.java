package com.watersolution.inventory.component.entity.employee.repository;

import com.watersolution.inventory.component.entity.employee.model.db.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
}
