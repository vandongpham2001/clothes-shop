package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CollectionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CollectionRepository extends PagingAndSortingRepository<CollectionEntity, Long> {
    List<CollectionEntity> findByStatus(Integer status);
}
