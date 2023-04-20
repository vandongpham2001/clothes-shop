package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.DistrictConverter;
import com.example.clothesshop.dto.response.DistrictDetailResponse;
import com.example.clothesshop.dto.response.DistrictResponse;
import com.example.clothesshop.entity.DistrictEntity;
import com.example.clothesshop.repository.DistrictRepository;
import com.example.clothesshop.service.IDistrictService;
import com.example.clothesshop.utils.ObjectMapperUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistrictService implements IDistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictConverter districtConverter;
    @Override
    public DistrictResponse findById(String id) {
        Optional<DistrictEntity> entity = districtRepository.findById(id);
        return districtConverter.toDTO(entity.get());
    }

    @Override
    public List<DistrictResponse> findByProvinceId(String province_id) {
        List<DistrictResponse> results;
        Iterable<DistrictEntity> entities;
        entities = districtRepository.findByProvinceCode(province_id);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), DistrictResponse.class);
        return results;
    }

    @Override
    public DistrictDetailResponse findDetailById(String id) {
        Optional<DistrictEntity> entity = districtRepository.findById(id);
        return districtConverter.toDetailDTO(entity.get());
    }
}
