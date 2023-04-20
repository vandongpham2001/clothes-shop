package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.WardConverter;
import com.example.clothesshop.dto.response.WardDetailResponse;
import com.example.clothesshop.dto.response.WardResponse;
import com.example.clothesshop.entity.WardEntity;
import com.example.clothesshop.repository.WardRepository;
import com.example.clothesshop.service.IWardService;
import com.example.clothesshop.utils.ObjectMapperUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WardService implements IWardService {
    @Autowired
    WardRepository wardRepository;
    @Autowired
    WardConverter wardConverter;

    @Override
    public WardResponse findById(String id) {
        Optional<WardEntity> entity = wardRepository.findById(id);
        return wardConverter.toDTO(entity.get());
    }

    @Override
    public List<WardResponse> findByDistrictId(String district_id) {
        List<WardResponse> results;
        Iterable<WardEntity> entities;
        entities = wardRepository.findByDistrictCode(district_id);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), WardResponse.class);
        return results;
    }

    @Override
    public WardDetailResponse findDetailById(String id) {
        Optional<WardEntity> entity = wardRepository.findById(id);
        return wardConverter.toDetailDTO(entity.get());
    }
}
