package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductColorImageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorImageRepository extends PagingAndSortingRepository<ProductColorImageEntity, Long> {
}
