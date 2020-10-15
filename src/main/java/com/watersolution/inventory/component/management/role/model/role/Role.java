package com.watersolution.inventory.component.management.role.model.role;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.role.model.privilege.Privilege;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = false)
public class Role extends Auditable {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonManagedReference(value = "role")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRole> userRoles;

    @JsonManagedReference(value = "privilege_role")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private List<Privilege> privileges;

    public Role() {
    }
}
