package com.watersolution.inventory.component.entity.employee.service;

import com.watersolution.inventory.component.common.model.api.PageDetails;
import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.employee.model.api.DesignationList;
import com.watersolution.inventory.component.entity.employee.model.api.EmployeeList;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.repository.DesignationRepository;
import com.watersolution.inventory.component.entity.employee.repository.EmployeeRepository;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.image.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DesignationRepository designationRepository;
    @Autowired
    private ImageUtil imageUtil;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public EmployeeList getAllEmployees(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Employee> employees = employeeRepository.findAllByStatusIn(Status.getAllStatusAsList(), PageRequest.of(page, pageDetails.getLimit()));
        employees.stream().map(this::setImage).collect(Collectors.toList());
        return new EmployeeList(employees.getContent(), employees.getTotalPages());
    }

    @Override
    public EmployeeList getAllActiveEmployees(PageDetails pageDetails) {
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
        if (employeeRepository.findByCode(employee.getCode()).isPresent()) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "This employee code is already exist", Collections.singletonList("This employee code is already exist"));
        }
        employee.setStatus(Status.ACTIVE.getValue());
        employee.fillCompulsory(employee.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employee.fillUpdateCompulsory(employee.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(String employeeId) {
        customValidator.validateFoundNull(employeeId, "employeeId");
        Employee employee = employeeRepository.findByIdAndStatusIn(Long.valueOf(employeeId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(employee, "employee");
        return employee;
    }

    @Override
    public Employee getActiveEmployeeById(long id) {
        return employeeRepository.findByIdAndStatus(id, Status.ACTIVE.getValue());
    }

    @Override
    public Employee suspendEmployee(TransactionRequest transactionRequest) {
        Employee employee = employeeRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        customValidator.validateFoundNull(employee, "employee");
        Status.validateState("Employee", employee.getStatus(), Status.ACTIVE);
        employee.setStatus(Status.SUSPENDED.getValue());
        employee.fillUpdateCompulsory(transactionRequest.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee activateEmployee(TransactionRequest transactionRequest) {
        Employee employee = employeeRepository.findByIdAndStatusIn(transactionRequest.getId(), Status.getAllStatusAsList());
        customValidator.validateFoundNull(employee, "employee");
        Status.validateState("Employee", employee.getStatus(), Status.SUSPENDED);
        employee.setStatus(Status.ACTIVE.getValue());
        employee.fillUpdateCompulsory(transactionRequest.getUserId());
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeList getByEmployeeDesignation(PageDetails pageDetails, String designation) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        Page<Employee> employees = employeeRepository.findAllByDesignationAndStatus(designation, Status.ACTIVE.getValue(), PageRequest.of(page, pageDetails.getLimit()));
        employees.stream().map(this::setImage).collect(Collectors.toList());
        return new EmployeeList(employees.getContent(), employees.getTotalPages());
    }

    @Override
    public DesignationList getDesignationList() {
        return new DesignationList(designationRepository.findAll());
    }

    private Employee setImage(Employee employee) {
        if (employee.getPhoto() != null) {
            employee.setPhoto(imageUtil.decompressBytes(employee.getPhoto()));
        }
        return employee;
    }

    private Page<Employee> searchEmployeeQuery(PageDetails pageDetails) {
        int page = pageDetails.getOffset() / pageDetails.getLimit();

        if (pageDetails.getSearchFilter().getName() != null && pageDetails.getSearchFilter().getType() != null) {
            return employeeRepository.findAllByNameLikeAndCodeLikeAndStatusIn(
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
            return employeeRepository.findAllByCodeLikeAndStatusIn(
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
