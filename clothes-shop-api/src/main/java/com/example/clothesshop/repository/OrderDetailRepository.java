package com.example.clothesshop.repository;

import com.example.clothesshop.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetailEntity, Long> {
    @Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id = ?1")
    List<OrderDetailEntity> findByOrderId(long order_id);
}
