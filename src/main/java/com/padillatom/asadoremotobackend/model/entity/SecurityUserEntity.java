package com.padillatom.asadoremotobackend.model.entity;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@AllArgsConstructor
public class SecurityUserEntity implements UserDetails, Serializable {

    private final UserEntity userEntity;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return userEntity.getAccountDetails().getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getAccountDetails().getEmail();
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
        return true;
    }
}
