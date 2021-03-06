package com.watersolution.inventory.component.entity.employee.repository;

import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByCode(String code);

    Page<Employee> findAllByStatus(int status, Pageable pageable);

    Page<Employee> findAllByNameLikeAndStatusIn(String name, List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByCodeLikeAndStatusIn(String code, List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByStatusIn(List<Integer> statusList, Pageable pageable);

    Page<Employee> findAllByNameLikeAndCodeLikeAndStatusIn(String name, String code, List<Integer> statusList, Pageable pageable);

    Employee findByIdAndStatus(long id, int status);

    Employee findByIdAndStatusIn(long id, List<Integer> statusList);

    Page<Employee> findAllByDesignationAndStatus(String designation, int status, Pageable pageable);
}
