package com.example.clothesshop.service;

import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.dto.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IUserService {
    Page<UserDTO> findAllPageable(Integer status, Pageable pageable);

    List<UserDTO> findAll(Integer status, Sort sort);

    UserDTO findById(long id);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    UserDTO save(UserRequest user);

    String delete(long[] ids);

    UserDTO addRoleToUser(String username, String roleName);

    UserDTO getUser(String username);

    List<UserDTO> getUsers();

    UserDTO getCurrentUserLogin();

    UserDTO getUserFromJWT(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
