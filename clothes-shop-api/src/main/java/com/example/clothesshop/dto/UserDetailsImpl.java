package com.example.clothesshop.dto;

import com.example.clothesshop.constant.SystemConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private UserDTO user;

    public UserDetailsImpl(UserDTO user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<RoleDTO> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleDTO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println("userDetailsImpl: " + role.getName());
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    public String getName() {
        return this.user.getName();
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public String getPhone() {
        return this.user.getPhone_number();
    }

    public String getAddress() {
        return this.user.getAddress();
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
        if (user.getStatus()== SystemConstant.ACTIVE_STATUS){
            return true;
        }
        return false;
    }
}