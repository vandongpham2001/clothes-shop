package com.example.clothesshop.service;

import com.example.clothesshop.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICartService {
    Page<CartDTO> findAllPageable(Pageable pageable);

    List<CartDTO> findAll(Sort sort);

    CartDTO findById(long id);

    CartDTO save(CartDTO dto);

    void delete(long[] ids);

    List<CartDTO> deleteCartByUserId(long user_id);

    Page<CartDTO> findAllByUserId(long user_id, Pageable pageable);

    List<CartDTO> findAllByUserId(long user_id);

    List<CartDTO> deleteCartByUsername(String username);

    Page<CartDTO> findAllByUsername(String username, Pageable pageable);

    List<CartDTO> findAllByUsername(String username);

    List<CartDTO> deleteCartByEmail(String email);

    Page<CartDTO> findAllByEmail(String email, Pageable pageable);

    List<CartDTO> findAllByEmail(String email);
}
