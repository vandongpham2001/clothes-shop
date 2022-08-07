package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CartEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartRepository extends PagingAndSortingRepository<CartEntity, Long> {
}
