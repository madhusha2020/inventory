package com.watersolution.inventory.component.entity.employee.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.model.api.EmployeeList;

public interface EmployeeService {

    EmployeeList getAllEmployees(PageDetails pageDetails);

    EmployeeList getAllActiveEmployees(PageDetails pageDetails);

    EmployeeList searchEmployees(PageDetails pageDetails);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployee(String employeeId);

    Employee getActiveEmployeeById(long id);

    Employee suspendEmployee(TransactionRequest transactionRequest);

    Employee activateEmployee(TransactionRequest transactionRequest);
}
