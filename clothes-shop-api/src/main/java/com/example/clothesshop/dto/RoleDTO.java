package com.example.clothesshop.dto;

import java.util.ArrayList;
import java.util.List;


public class RoleDTO extends AbstractDTO<RoleDTO> {
    private String name;
    private String role;
    private List<UserDTO> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
