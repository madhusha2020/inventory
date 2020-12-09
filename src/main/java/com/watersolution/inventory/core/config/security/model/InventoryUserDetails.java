package com.watersolution.inventory.core.config.security.model;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.user.model.db.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InventoryUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean active;
    private final boolean locked;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public InventoryUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.locked = user.getFailedAttempts() >= 3;
        this.active = user.getStatus() == Status.ACTIVE.getValue();

        user.getUserRoles().stream().forEach(userRole -> {
            if (userRole.getStatus() != Status.DELETED.getValue()) {
                userRole.getRole().getPrivileges().stream().forEach(privilege -> {
                    if (privilege.getStatus() != Status.DELETED.getValue()) {
                        authorities.add(new SimpleGrantedAuthority(privilege.getModule().getPermissionCode()));
                    }
                });
            }
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
