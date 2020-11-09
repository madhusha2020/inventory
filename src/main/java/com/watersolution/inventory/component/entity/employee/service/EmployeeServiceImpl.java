package com.watersolution.inventory.component.entity.employee.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.employee.model.api.EmployeeList;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.repository.EmployeeRepository;
import com.watersolution.inventory.component.management.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public EmployeeList getAllEmployees(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Employee> employees = employeeRepository.findAllByStatus(Status.ACTIVE.getValue(), PageRequest.of(page, pageDetails.getLimit()));
        employees.stream().map(this::setImage).collect(Collectors.toList());
        return new EmployeeList(employees.getContent(), employees.getTotalPages());
    }

    @Override
    public EmployeeList searchEmployees(PageDetails pageDetails) {
        customValidator.validateFoundNull(pageDetails.getSearchFilter(), ErrorCodes.SEARCH_FILTER);
        customValidator.validateNullOrEmpty(pageDetails.getSearchFilter().getStatusList(), ErrorCodes.STATUS_LIST);
        Page<Employee> employees = searchEmployeeQuery(pageDetails);
        employees.stream().map(this::setImage).collect(Collectors.toList());
        return new EmployeeList(employees.getContent(), employees.getTotalPages());
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setStatus(Status.ACTIVE.getValue());
        employee.fillCompulsory(employee.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employee.setStatus(Status.ACTIVE.getValue());
        employee.setModifieddate(new Date());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(long id) {
        Employee employee = employeeRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
        if (employee.getPhoto() != null) {
            employee.setPhoto(imageUtil.decompressBytes(employee.getPhoto()));
        }
        return employee;
    }

    private Employee setImage(Employee employee){
        employee.setPhoto(imageUtil.decompressBytes(employee.getPhoto()));
        return employee;
    }

    private Page<Employee> searchEmployeeQuery(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        if (pageDetails.getSearchFilter().getName() != null && pageDetails.getSearchFilter().getType() != null) {
            return employeeRepository.findAllByNameLikeAndNicLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getName() != null) {
            return employeeRepository.findAllByNameLikeAndStatusIn(
                    pageDetails.getSearchFilter().getName(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else if (pageDetails.getSearchFilter().getType() != null) {
            return employeeRepository.findAllByNicLikeAndStatusIn(
                    pageDetails.getSearchFilter().getType(),
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        } else {
            return employeeRepository.findAllByStatusIn(
                    pageDetails.getSearchFilter().getStatusList(),
                    PageRequest.of(page, pageDetails.getLimit()));
        }
    }
}
