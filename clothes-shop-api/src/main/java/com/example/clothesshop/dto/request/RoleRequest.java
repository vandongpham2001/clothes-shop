package com.example.clothesshop.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest extends AbstractRequest<RoleRequest> {
    private String name;
    private String role;
    private Integer status;
}
