package com.example.clothesshop.repository;

import com.example.clothesshop.entity.SizeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SizeRepository extends PagingAndSortingRepository<SizeEntity, Long> {
    Page<SizeEntity> findByStatus(Integer status, Pageable pageable);

    Iterable<SizeEntity> findByStatus(Integer status, Sort sort);

    @Query("select s from SizeEntity s where s.status=?1 and s.id in (select pcs.size.id from ProductColorSizeEntity pcs where pcs.product_color.id in (select pc.id from ProductColorEntity pc join ProductEntity p on  p.id = pc.product.id where pc.product.id=?2))")
    Page<SizeEntity> findSizeEntitiesByStatusAndProductsId(Integer status, Long product_id, Pageable pageable);

    @Query("select s from SizeEntity s where s.status=?1 and s.id in (select pcs.size.id from ProductColorSizeEntity pcs where pcs.product_color.id in (select pc.id from ProductColorEntity pc join ProductEntity p on  p.id = pc.product.id where pc.product.id=?2))")
    Iterable<SizeEntity> findSizeEntitiesByStatusAndProductsId(Integer status, Long product_id, Sort sort);
}