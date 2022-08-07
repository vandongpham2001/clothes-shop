package com.example.clothesshop.repository;

import com.example.clothesshop.entity.OrderEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
}
