package com.example.clothesshop.repository;

import com.example.clothesshop.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SizeRepository extends PagingAndSortingRepository<SizeEntity, Long> {
    Page<SizeEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<SizeEntity> findByStatus(Integer status, Sort sort);
}
