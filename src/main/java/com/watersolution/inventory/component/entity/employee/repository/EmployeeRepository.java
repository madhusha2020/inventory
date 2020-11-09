package com.watersolution.inventory.component.entity.employee.repository;

import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByIdAndStatus(long id, int status);
}
