package com.example.clothesshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractDTO<UserDTO> {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private List<CommentDTO> comments = new ArrayList<>();
    private List<CartDTO> carts = new ArrayList<>();
    private List<OrderDTO> orders = new ArrayList<>();
    private List<NewDTO> news = new ArrayList<>();
    private Collection<RoleDTO> roles = new ArrayList<>();
}
