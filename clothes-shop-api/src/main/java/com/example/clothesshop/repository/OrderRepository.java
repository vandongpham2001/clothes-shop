package com.example.clothesshop.repository;

import com.example.clothesshop.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
    Page<OrderEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<OrderEntity> findByStatus(Integer status, Sort sort);
}
