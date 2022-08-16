package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findByStatus(Integer status, Pageable pageable);
    Iterable<CategoryEntity> findByStatus(Integer status, Sort sort);
    @Query("SELECT c FROM CategoryEntity c where c.status=1 and c.id in (SELECT c.parent_id FROM CategoryEntity c)")
    List<CategoryEntity> findParentCategory();
    @Query("SELECT c FROM CategoryEntity c where c.parent_id=?1")
    List<CategoryEntity> findByParentId(Long parent_id);
    Optional<CategoryEntity> findById(Long id);
}
