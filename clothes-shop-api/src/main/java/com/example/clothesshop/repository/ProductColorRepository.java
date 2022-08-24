package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductColorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductColorRepository extends PagingAndSortingRepository<ProductColorEntity, Long> {
    @Query("SELECT p FROM ProductColorEntity p WHERE p.product.id = ?1")
    List<ProductColorEntity> findByProductId(Long product_id);
}
