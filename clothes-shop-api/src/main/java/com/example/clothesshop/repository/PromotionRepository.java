package com.example.clothesshop.repository;

import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PromotionRepository extends PagingAndSortingRepository<PromotionEntity, Long> {
    Page<PromotionEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<PromotionEntity> findByStatus(Integer status, Sort sort);
}
