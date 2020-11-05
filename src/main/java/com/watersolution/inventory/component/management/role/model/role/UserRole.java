package com.watersolution.inventory.component.management.role.model.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.entity.user.model.db.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "userrole")
@EqualsAndHashCode(callSuper = false)
public class UserRole extends Auditable {

    @EmbeddedId
    private UserRoleId userRoleId;

    @JsonBackReference(value = "user")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userName")
    @JoinColumn(name = "user_username")
    private User user;

    @JsonBackReference(value = "role")
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("roleName")
    @JoinColumn(name = "role_name")
    private Role role;

    public UserRole() {
    }
}
