package com.example.clothesshop.service;

import com.example.clothesshop.dto.response.ProvinceDetailResponse;
import com.example.clothesshop.dto.response.ProvinceResponse;

import java.util.List;

public interface IProvinceService {
    List<ProvinceDetailResponse> findAll();
    ProvinceResponse findById(String id);
    ProvinceDetailResponse findDetailById(String id);
}
