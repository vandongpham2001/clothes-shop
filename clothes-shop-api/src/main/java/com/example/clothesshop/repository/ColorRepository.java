package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ColorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColorRepository extends PagingAndSortingRepository<ColorEntity, Long> {
    Page<ColorEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<ColorEntity> findByStatus(Integer status, Sort sort);
    @Query("select c from ColorEntity c join ProductColorEntity p on c.id = p.color.id where c.status = ?1 and p.product.id = ?2")
    Page<ColorEntity> findColorEntitiesByStatusAndProductsId(Integer status, Long product_id, Pageable pageable);
    @Query("select c from ColorEntity c join ProductColorEntity p on c.id = p.color.id where c.status = ?1 and p.product.id = ?2")
    Iterable<ColorEntity> findColorEntitiesByStatusAndProductsId(Integer status, Long product_id, Sort sort);
}
