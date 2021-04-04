package com.watersolution.inventory.component.entity.employee.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "designation")
@EqualsAndHashCode(callSuper = false)
public class Designation extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "designation_seq", name = "designation_seq")
    private long id;

    @NotBlank(message = "Employee designation must not be blank")
    @Column(name = "name")
    private String name;

    public Designation() {
    }
}
