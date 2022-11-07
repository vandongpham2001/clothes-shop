package com.example.clothesshop.repository;

import com.example.clothesshop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "select p from ProductEntity p left join p.promotions pro where pro.status=?1 and current_date between pro.start_date and pro.end_date")
    List<ProductEntity> findByCurrentPromotionsStatus(Integer status);

//    , nativeQuery = true
    @Query(value = "select p from ProductEntity p where p.status=1")
    Page<ProductEntity> findProductEntities(Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.status=1")
    Iterable<ProductEntity> findProductEntities(Sort sort);

    @Query(value = "select p from ProductEntity p  where p.status=1 and p.category.id=?1")
    Page<ProductEntity> findProductEntitiesByCategory(Long category_id, Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.status=1 and p.category.id=?1")
    Iterable<ProductEntity> findProductEntitiesByCategory(Long category_id, Sort sort);

    @Query(value = "select p from ProductEntity p where p.status=1 and p.name like %:keyword%")
    Page<ProductEntity> findProductEntitiesByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select p from ProductEntity p where p.status=1 and p.name like %:keyword%")
    Iterable<ProductEntity> findProductEntitiesByKeyword(@Param("keyword") String keyword, Sort sort);

    @Query(value = "select p from ProductEntity p left join p.product_color pro left join pro.product_color_size pcs " +
            "where p.status=1 and p.price between :min_price and :max_price and " +
            "(:color is null or pro.color.id=:color) and " +
            "(:size is null or pcs.size.id=:size) " +
            "group by p")
    Page<ProductEntity> findProductEntitiesByFilter(@Param("color") Long color_id, @Param("size") Long size_id, @Param("min") BigDecimal min_price, @Param("max") BigDecimal max_price, Pageable pageable);

    @Query(value = "select p from ProductEntity p left join p.product_color pro left join pro.product_color_size pcs " +
            "where p.status=1 and p.price between :min_price and :max_price and " +
            "(:color is null or pro.color.id=:color) and " +
            "(:size is null or pcs.size.id=:size) " +
            "group by p")
    Iterable<ProductEntity> findProductEntitiesByFilter(@Param("color") Long color_id, @Param("size") Long size_id, @Param("min_price") BigDecimal min_price, @Param("max_price") BigDecimal max_price, Sort sort);

    @Query(value = "from ProductEntity p left join p.promotions pro where p.status=1 and p.id=?1")
    ProductEntity findDetailProductById(Long id);

    @Query("select max(price) from ProductEntity")
    BigDecimal findMaxPriceProduct();

    Optional<ProductEntity> findById(Long id);
}
