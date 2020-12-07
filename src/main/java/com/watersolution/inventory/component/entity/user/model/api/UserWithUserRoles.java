package com.watersolution.inventory.component.entity.user.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.user.model.db.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserWithUserRoles extends ResponseDefault {
    @Valid
    private User user;
    private List<String> roleNameList;

    public UserWithUserRoles(){
    }

    public UserWithUserRoles(@Valid User user, List<String> roleNameList) {
        this.user = user;
        this.roleNameList = roleNameList;
    }
}
