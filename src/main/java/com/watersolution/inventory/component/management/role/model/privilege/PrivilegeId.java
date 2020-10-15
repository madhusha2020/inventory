package com.watersolution.inventory.component.management.role.model.privilege;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class PrivilegeId implements Serializable {

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "module_id")
    private long moduleId;

    public PrivilegeId() {
    }

    public PrivilegeId(String roleName, long moduleId) {
        this.roleName = roleName;
        this.moduleId = moduleId;
    }
}
