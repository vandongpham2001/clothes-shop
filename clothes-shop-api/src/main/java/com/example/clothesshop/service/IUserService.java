package com.example.clothesshop.service;

import com.example.clothesshop.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO user);
    UserDTO addRoleToUser(String username, String roleName);
    UserDTO getUser(String username);
    List<UserDTO> getUsers();
    UserDTO getCurrentUserLogin();
}
