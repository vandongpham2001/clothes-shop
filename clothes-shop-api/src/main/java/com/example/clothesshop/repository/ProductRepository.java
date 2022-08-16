package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    Page<ProductEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<ProductEntity> findByStatus(Integer status, Sort sort);
}
