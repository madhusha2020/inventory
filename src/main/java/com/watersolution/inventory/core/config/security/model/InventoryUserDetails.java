package com.watersolution.inventory.core.config.security.model;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.component.management.role.model.role.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InventoryUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public InventoryUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.getStatus() == Status.ACTIVE.getValue();
        user.getUserRoles().stream().forEach(this::setAuthority);
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    private void setAuthority(UserRole userRole) {
        authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
    }
}
