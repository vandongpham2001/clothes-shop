package com.example.clothesshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends AbstractDTO<RoleDTO> {
    private String name;
    private String role;
    @JsonIgnore
    private List<UserDTO> users = new ArrayList<>();
}
