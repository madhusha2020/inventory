package com.watersolution.inventory.component.entity.user.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.user.model.db.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserList extends ResponseDefault {
    private List<User> userList;

    public UserList(List<User> userList) {
        this.userList = userList;
    }
}
