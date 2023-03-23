package com.example.clothesshop.repository;

import com.example.clothesshop.entity.DistrictEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DistrictRepository extends PagingAndSortingRepository<DistrictEntity, String> {
    Iterable<DistrictEntity> findByProvinceCode(String province_code);
}
