package com.example.clothesshop.repository;

import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PromotionRepository extends PagingAndSortingRepository<PromotionEntity, Long> {
}
