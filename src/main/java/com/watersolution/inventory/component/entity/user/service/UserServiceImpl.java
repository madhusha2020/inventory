package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.common.model.api.TransactionRequest;
import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.customer.service.CustomerService;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.service.EmployeeService;
import com.watersolution.inventory.component.entity.supplier.model.db.Supplier;
import com.watersolution.inventory.component.entity.supplier.service.SupplierService;
import com.watersolution.inventory.component.entity.user.model.api.*;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.component.entity.user.repository.UserRepository;
import com.watersolution.inventory.component.entity.user.util.UserServiceHelper;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.role.model.role.Role;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import com.watersolution.inventory.component.management.role.model.role.UserRoleId;
import com.watersolution.inventory.component.management.role.util.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceHelper userServiceHelper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomValidator customValidator;

    @Override
    public User registerUser(User user) {
        userServiceHelper.validateUserName(user);
        user.setStatus(Status.ACTIVE.getValue());
        user.fillCompulsory(user.getUserId());
        user.setFailedAttempts(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(new UserRoleId(user.getUserName(), Roles.ROLE_CUSTOMER.getValue()));
        userRole.setUser(user);
        userRole.fillCompulsory(user.getUserId());
        userRole.setRole(new Role(Roles.ROLE_CUSTOMER.getValue()));

        user.setUserRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }

    @Override
    public UserList getAllUsers() {
        return new UserList(userRepository.findByStatusIn(Status.getAllStatusAsList()));
    }

    @Override
    public UserList getAllActiveUsers() {
        return new UserList(userRepository.findByStatus(Status.ACTIVE.getValue()));
    }

    @Transactional
    @Override
    public UserWithUserRoles getUserByUserName(String userName) {
        customValidator.validateNullOrEmpty(userName, "userName");

        User user = userRepository.findByUserNameAndStatusIn(userName, Status.getAllStatusAsList());
        customValidator.validateFoundNull(user, "user");

        return new UserWithUserRoles(user, getRoleNameList(user));
    }

    @Override
    public User activateUser(TransactionRequest transactionRequest) {
        customValidator.validateNullOrEmpty(transactionRequest.getEmail(), "userName");
        User user = userRepository.findByUserNameAndStatusIn(transactionRequest.getEmail(), Status.getAllStatusAsList());
        customValidator.validateFoundNull(user, "user");
        Status.validateState("User", user.getStatus(), Status.SUSPENDED);
        user.setStatus(Status.ACTIVE.getValue());
        user.fillUpdateCompulsory(transactionRequest.getUserId());
        return userRepository.save(user);
    }

    @Override
    public User suspendUser(TransactionRequest transactionRequest) {
        customValidator.validateNullOrEmpty(transactionRequest.getEmail(), "userName");
        User user = userRepository.findByUserNameAndStatusIn(transactionRequest.getEmail(), Status.getAllStatusAsList());
        customValidator.validateFoundNull(user, "user");
        Status.validateState("User", user.getStatus(), Status.ACTIVE);
        user.setStatus(Status.SUSPENDED.getValue());
        user.fillUpdateCompulsory(transactionRequest.getUserId());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public CustomerUser getCustomerById(String id) {
        customValidator.validateNullOrEmpty(id, "id");

        Customer customer = customerService.getCustomerById(id);
        log.info("Customer : {}", customer.toString());

        User user = userRepository.findByUserNameAndStatusIn(customer.getEmail(), Status.getAllStatusAsList());
        log.info("User : {}", user.toString());

        return new CustomerUser(user, customer, getRoleNameList(user));
    }

    @Transactional
    @Override
    public CustomerUser saveCustomer(CustomerUser customerUser) {
        customerUser.setUser(userServiceHelper.saveUserWithRoles(customerUser.getUser(), customerUser.getRoleNameList()));
        customerUser.setCustomer(customerService.saveCustomer(customerUser.getCustomer()));
        return customerUser;
    }

    @Transactional
    @Override
    public CustomerUser updateCustomer(CustomerUser customerUser) {
        if (customerService.getActiveCustomerById(customerUser.getCustomer().getId()) == null) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid or inactive customer", Collections.singletonList("Invalid or inactive customer"));
        }
        customerUser.setUser(userServiceHelper.updateUserWithRoles(customerUser.getUser(), customerUser.getRoleNameList()));
        customerUser.setCustomer(customerService.updateCustomer(customerUser.getCustomer()));
        return customerUser;
    }

    @Transactional
    @Override
    public EmployeeUser getEmployeeById(String id) {
        customValidator.validateNullOrEmpty(id, "id");

        Employee employee = employeeService.getEmployee(id);
        log.info("Employee : {}", employee.toString());

        User user = userRepository.findByUserNameAndStatusIn(employee.getEmail(), Status.getAllStatusAsList());
        log.info("User : {}", user.toString());

        return new EmployeeUser(user, employee, getRoleNameList(user));
    }

    @Transactional
    @Override
    public EmployeeUser saveEmployee(EmployeeUser employeeUser) {
        employeeUser.setUser(userServiceHelper.saveUserWithRoles(employeeUser.getUser(), employeeUser.getRoleNameList()));
        employeeUser.setEmployee(employeeService.saveEmployee(employeeUser.getEmployee()));
        return employeeUser;
    }

    @Transactional
    @Override
    public EmployeeUser updateEmployee(EmployeeUser employeeUser) {
        if (employeeService.getActiveEmployeeById(employeeUser.getEmployee().getId()) == null) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid or inactive employee", Collections.singletonList("Invalid or inactive employee"));
        }
        employeeUser.setUser(userServiceHelper.updateUserWithRoles(employeeUser.getUser(), employeeUser.getRoleNameList()));
        employeeUser.setEmployee(employeeService.updateEmployee(employeeUser.getEmployee()));
        return employeeUser;
    }

    @Transactional
    @Override
    public SupplierUser getSupplierById(String id) {
        customValidator.validateNullOrEmpty(id, "id");

        Supplier supplier = supplierService.getSupplier(id);
        log.info("Supplier : {}", supplier.toString());

        User user = userRepository.findByUserNameAndStatusIn(supplier.getEmail(), Status.getAllStatusAsList());
        log.info("User : {}", user.toString());

        return new SupplierUser(user, supplier, getRoleNameList(user));
    }

    @Transactional
    @Override
    public SupplierUser saveSupplier(SupplierUser supplierUser) {
        supplierUser.setUser(userServiceHelper.saveUserWithRoles(supplierUser.getUser(), supplierUser.getRoleNameList()));
        supplierUser.setSupplier(supplierService.saveSupplier(supplierUser.getSupplier()));
        return supplierUser;
    }

    @Transactional
    @Override
    public SupplierUser updateSupplier(SupplierUser supplierUser) {
        if (supplierService.getActiveSupplierById(supplierUser.getSupplier().getId()) == null) {
            throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid or inactive supplier", Collections.singletonList("Invalid or inactive supplier"));
        }
        supplierUser.setUser(userServiceHelper.updateUserWithRoles(supplierUser.getUser(), supplierUser.getRoleNameList()));
        supplierUser.setSupplier(supplierService.updateSupplier(supplierUser.getSupplier()));
        return supplierUser;
    }

    @Override
    public void updateFailedAttempts(User user) {
        Optional<User> updatableUser = userServiceHelper.getActiveUserByUserName(user);
        if (updatableUser.isPresent()) {
            updatableUser.get().setFailedAttempts(updatableUser.get().getFailedAttempts() + 1);
            userRepository.save(updatableUser.get());
        }
    }

    @Override
    public void resetFailedAttempts(User user) {
        Optional<User> updatableUser = userServiceHelper.getActiveUserByUserName(user);
        if (updatableUser.isPresent()) {
            updatableUser.get().setFailedAttempts(0);
            userRepository.save(updatableUser.get());
        }
    }

    private List<String> getRoleNameList(User user) {
        List<String> roleNameList = new ArrayList<>();
        user.getUserRoles().stream().forEach(userRole -> {
            if (userRole.getStatus() != Status.DELETED.getValue()) {
                roleNameList.add(userRole.getUserRoleId().getRoleName());
            }
        });
        return roleNameList;
    }
}
