package com.watersolution.inventory.component.entity.user.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable {

    @Id
    @NotBlank(message = "Username must not be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @ToString.Exclude
    @NotBlank(message = "Password must not be blank")
    @Column(name = "password")
    private String password;

    @Column(name = "failedattempts")
    private Integer failedAttempts;

    @Transient
    private String id;

    @Transient
    private String token;

    @ToString.Exclude
    @Transient
    private String oldPassword;

    @ToString.Exclude
    @JsonManagedReference(value = "user")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserRole> userRoles;

    public User() {
    }
}
