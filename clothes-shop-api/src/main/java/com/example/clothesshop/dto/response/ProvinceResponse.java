package com.example.clothesshop.dto.response;

import lombok.Data;

@Data
public class ProvinceResponse {
    private String code;
    private String name;
    private String name_en;
    private String full_name;
    private String full_name_en;
    private String code_name;
//    List<DistrictResponse> districts = new ArrayList<>();
}
