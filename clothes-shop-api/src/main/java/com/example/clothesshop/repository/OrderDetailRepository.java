package com.example.clothesshop.repository;

import com.example.clothesshop.entity.OrderDetailEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetailEntity, Long> {
}
