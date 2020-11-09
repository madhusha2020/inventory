package com.watersolution.inventory.component.entity.employee.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeList extends ResponseDefault {
    @Valid
    private List<Employee> employees;
    private int totalPages;

    public EmployeeList(List<Employee> employees, int totalPages) {
        this.employees = employees;
        this.totalPages = totalPages;
    }
}
