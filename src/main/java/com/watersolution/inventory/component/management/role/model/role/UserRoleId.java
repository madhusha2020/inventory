package com.watersolution.inventory.component.management.role.model.role;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "user_username")
    private String userName;

    @Column(name = "role_name")
    private String roleName;

    public UserRoleId() {
    }

    public UserRoleId(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }
}
