package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.ProvinceConverter;
import com.example.clothesshop.dto.response.ListProvinceResponse;
import com.example.clothesshop.dto.response.ProvinceResponse;
import com.example.clothesshop.entity.ProvinceEntity;
import com.example.clothesshop.repository.ProvinceRepository;
import com.example.clothesshop.service.IProvinceService;
import com.example.clothesshop.utils.ObjectMapperUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProvinceService implements IProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    ProvinceConverter provinceConverter;

    @Override
    public List<ListProvinceResponse> findAll() {
        List<ListProvinceResponse> results;
        Iterable<ProvinceEntity> entities;
        entities = provinceRepository.findAll();
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ListProvinceResponse.class);
        return results;
    }

    @Override
    public ProvinceResponse findById(String id) {
        Optional<ProvinceEntity> entity = provinceRepository.findById(id);
        return provinceConverter.toDTO(entity.get());
    }
}
