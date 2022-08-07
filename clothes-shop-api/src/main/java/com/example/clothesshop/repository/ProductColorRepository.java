package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductColorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorRepository extends PagingAndSortingRepository<ProductColorEntity, Long> {
}
