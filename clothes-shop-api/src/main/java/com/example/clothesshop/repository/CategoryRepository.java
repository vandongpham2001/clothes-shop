package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<CategoryEntity> findByStatus(Integer status, Sort sort);
}
