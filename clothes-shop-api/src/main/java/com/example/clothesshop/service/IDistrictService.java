package com.example.clothesshop.service;

import com.example.clothesshop.dto.response.DistrictResponse;

import java.util.List;

public interface IDistrictService {
    DistrictResponse findById(String id);
    List<DistrictResponse> findByProvinceId(String province_id);
}
