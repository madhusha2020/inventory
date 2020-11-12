package com.watersolution.inventory.component.entity.employee.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.common.validator.DateValidateConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employee")
@EqualsAndHashCode(callSuper = false)
public class Employee extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "employee_seq", name = "employee_seq")
    private long id;

    @NotBlank(message = "Employee code must not be blank")
    @Column(name = "code")
    private String code;

    @Column(name = "callingname")
    private String callingname;

    @NotBlank(message = "Name must not be blank")
    @Column(name = "name")
    private String name;

    @DateValidateConstraint(message = "Invalid birth date")
    @Column(name = "dobirth")
    private LocalDate dobirth;

    @NotBlank(message = "NIC must not be blank")
    @Column(name = "nic")
    private String nic;

    @NotBlank(message = "Address must not be blank")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Mobile must not be blank")
    @Column(name = "mobile")
    private String mobile;

    @Column(name = "land")
    private String land;

    @Column(name = "description")
    private String description;

    @DateValidateConstraint(message = "Invalid recruited date")
    @Column(name = "dorecruite")
    private LocalDate dorecruite;

    @Column(name = "photo",  columnDefinition = "LONGBLOB")
    private byte[] photo;

    @NotBlank(message = "Employee gender must not be blank")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Employee civil status must not be blank")
    @Column(name = "civilstatus")
    private String civilstatus;

    @NotBlank(message = "Employee title must not be blank")
    @Column(name = "nametitle")
    private String nametitle;
}
