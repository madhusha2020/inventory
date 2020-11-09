package com.watersolution.inventory.component.entity.employee.repository;

import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByStatus(int status, Pageable pageable);

    Page<Employee> findAllByNameLikeAndStatusIn(String name, List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByNicLikeAndStatusIn(String nic, List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByStatusIn(List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByNameLikeAndNicLikeAndStatusIn(String name, String nic, List<Integer> statusList, Pageable pageable);

    Employee findByIdAndStatus(long id, int status);
}
