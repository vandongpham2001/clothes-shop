package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColorRepository extends PagingAndSortingRepository<ColorEntity, Long> {
    Page<ColorEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<ColorEntity> findByStatus(Integer status, Sort sort);
}
