package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductColorSizeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorSizeRepository extends PagingAndSortingRepository<ProductColorSizeEntity, Long> {
}
