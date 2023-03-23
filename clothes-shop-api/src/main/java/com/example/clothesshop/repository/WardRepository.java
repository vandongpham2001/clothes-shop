package com.example.clothesshop.repository;

import com.example.clothesshop.entity.WardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WardRepository extends PagingAndSortingRepository<WardEntity, String> {
    Iterable<WardEntity> findByDistrictCode(String ward_code);
}
