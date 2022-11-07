package com.example.clothesshop.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SizeOfProductColorResponse {
    private Long id;
    private ColorResponse color;
    private String thumbnail;
}
