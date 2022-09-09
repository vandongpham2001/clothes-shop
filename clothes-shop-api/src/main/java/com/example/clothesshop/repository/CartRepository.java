package com.example.clothesshop.repository;

import com.example.clothesshop.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CartRepository extends PagingAndSortingRepository<CartEntity, Long> {
    @Modifying
    @Query("DELETE CartEntity c WHERE c.user.id = ?1")
    void deleteByUserId(long user_id);

    @Query("SELECT c FROM CartEntity c WHERE c.user.id = ?1 ORDER BY c.createdDate DESC")
    Page<CartEntity> findAllByUserId(long user_id, Pageable pageable);

    @Query("SELECT c FROM CartEntity c WHERE c.user.id = ?1 ORDER BY c.createdDate DESC")
    List<CartEntity> findAllByUserId(long user_id);

    @Modifying
    @Query("DELETE CartEntity c WHERE c.user.username = ?1")
    void deleteByUsername(String username);

    @Query("SELECT c FROM CartEntity c WHERE c.user.username = ?1 ORDER BY c.createdDate DESC")
    Page<CartEntity> findAllByUsername(String username, Pageable pageable);

    @Query("SELECT c FROM CartEntity c WHERE c.user.username = ?1 ORDER BY c.createdDate DESC")
    List<CartEntity> findAllByUsername(String username);

    @Modifying
    @Query("DELETE CartEntity c WHERE c.user.email = ?1")
    void deleteByEmail(String email);

    @Query("SELECT c FROM CartEntity c WHERE c.user.email = ?1 ORDER BY c.createdDate DESC")
    Page<CartEntity> findAllByEmail(String email, Pageable pageable);

    @Query("SELECT c FROM CartEntity c WHERE c.user.email = ?1 ORDER BY c.createdDate DESC")
    List<CartEntity> findAllByEmail(String email);
}
