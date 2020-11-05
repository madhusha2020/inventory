package com.watersolution.inventory.component.management.role.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.role.model.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "privilege")
@EqualsAndHashCode(callSuper = false)
public class Privilege extends Auditable {

    @EmbeddedId
    private PrivilegeId privilegeId;

    @JsonBackReference(value = "privilege_role")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleName")
    @JoinColumn(name = "role_name")
    private Role role;

    @JsonBackReference(value = "module")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("moduleId")
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "getAll")
    private String getAll;

    @Column(name = "getBasic")
    private String getBasic;

    @Column(name = "added")
    private String add;

    @Column(name = "updated")
    private String update;

    @Column(name = "deleted")
    private String delete;

    public Privilege() {
    }
}
