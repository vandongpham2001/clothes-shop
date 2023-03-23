package com.example.clothesshop.dto.response;

import lombok.Data;

@Data
public class DistrictResponse {
    private String code;
    private String name;
    private String name_en;
    private String full_name;
    private String full_name_en;
    private String code_name;
//    List<WardResponse> wards = new ArrayList<>();
}
