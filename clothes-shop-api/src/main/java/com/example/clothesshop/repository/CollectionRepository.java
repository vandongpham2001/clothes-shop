package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollectionRepository extends PagingAndSortingRepository<CollectionEntity, Long> {
    Page<CollectionEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<CollectionEntity> findByStatus(Integer status, Sort sort);
}
