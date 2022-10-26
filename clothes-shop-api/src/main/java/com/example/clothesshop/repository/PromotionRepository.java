package com.example.clothesshop.repository;

import com.example.clothesshop.entity.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PromotionRepository extends PagingAndSortingRepository<PromotionEntity, Long> {
    Page<PromotionEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<PromotionEntity> findByStatus(Integer status, Sort sort);
    @Query("select p from PromotionEntity p where p.status = 1 and CURRENT_DATE BETWEEN p.start_date AND p.end_date")
    List<PromotionEntity> findCurrent();
}
