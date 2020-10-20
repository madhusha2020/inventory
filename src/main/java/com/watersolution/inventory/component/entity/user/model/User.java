package com.watersolution.inventory.component.entity.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "failedattempts")
    private int failedAttempts;

    @Transient
    private String id;

    @Transient
    private String token;

    @JsonManagedReference(value = "user")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> userRoles;

    public User() {
    }
}