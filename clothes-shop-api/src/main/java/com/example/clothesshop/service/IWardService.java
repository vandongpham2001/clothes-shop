package com.example.clothesshop.service;

import com.example.clothesshop.dto.response.WardResponse;

import java.util.List;

public interface IWardService {
    WardResponse findById(String id);
    List<WardResponse> findByDistrictId(String district_id);
}
