package com.example.clothesshop.service;

import com.example.clothesshop.dto.response.ProvinceResponse;

import java.util.List;

public interface IProvinceService {
    List<ProvinceResponse> findAll();
    ProvinceResponse findById(String id);
}
