package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
}
