package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    Page<ProductEntity> findByStatus(Integer status, Pageable pageable);

    Iterable<ProductEntity> findByStatus(Integer status, Sort sort);

    Page<ProductEntity> findByStatusAndCategoryId(Integer status, Long category_id, Pageable pageable);

    Iterable<ProductEntity> findByStatusAndCategoryId(Integer status, Long category_id, Sort sort);

    Page<ProductEntity> findProductEntitiesByStatusAndPromotionsId(Integer status, Long promotion_id, Pageable pageable);

    Iterable<ProductEntity> findProductEntitiesByStatusAndPromotionsId(Integer status, Long collection_id, Sort sort);

    Page<ProductEntity> findProductEntitiesByStatusAndCollectionsId(Integer status, Long promotion_id, Pageable pageable);

    Iterable<ProductEntity> findProductEntitiesByStatusAndCollectionsId(Integer status, Long collection_id, Sort sort);

    @Query("select max(price) from ProductEntity")
    BigDecimal findMaxPriceProduct();

    Optional<ProductEntity> findById(Long id);




}
