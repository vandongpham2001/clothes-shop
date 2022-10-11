package com.example.clothesshop.repository;

import com.example.clothesshop.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
    Page<OrderEntity> findByStatus(Integer status, Pageable pageable);

    Iterable<OrderEntity> findByStatus(Integer status, Sort sort);

    @Query("SELECT o FROM OrderEntity o WHERE o.user.username = ?1")
    Page<OrderEntity> findByUsername(String username, Pageable pageable);

    @Query("SELECT o FROM OrderEntity o WHERE o.user.username = ?1")
    List<OrderEntity> findByUsername(String username, Sort sort);

    @Query("SELECT o FROM OrderEntity o WHERE o.user.username = ?1 AND o.status = ?2")
    Page<OrderEntity> findByStatusAndUsername(String username, Integer status, Pageable pageable);

    @Query("SELECT o FROM OrderEntity o WHERE o.user.username = ?1 AND o.status = ?2")
    List<OrderEntity> findByStatusAndUsername(String username, Integer status, Sort sort);
}
