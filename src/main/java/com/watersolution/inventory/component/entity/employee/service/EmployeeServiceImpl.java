package com.watersolution.inventory.component.entity.employee.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.employee.model.api.EmployeeList;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.repository.EmployeeRepository;
import com.watersolution.inventory.component.management.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ImageUtil imageUtil;

    @Override
    public EmployeeList getAllEmployees(PageDetails pageDetails) {
        return null;
    }

    @Override
    public EmployeeList searchEmployees(PageDetails pageDetails) {
        return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setStatus(Status.ACTIVE.getValue());
        employee.fillCompulsory(employee.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployee(long id) {
        Employee employee = employeeRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if(employee.getPhoto() != null){
            employee.setPhoto(imageUtil.decompressBytes(employee.getPhoto()));
        }
        return employee;
    }
}
