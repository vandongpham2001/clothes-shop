package com.example.clothesshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends AbstractRequest<UserRequest> {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private Integer status;
    private MultipartFile file;
}
