package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductColorImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorImageRepository extends PagingAndSortingRepository<ProductColorImageEntity, Long> {
    @Modifying
    @Query("DELETE ProductColorImageEntity p WHERE p.product_color.id=?1")
    void deleteByProduct_color(Long product_color_id);
}
