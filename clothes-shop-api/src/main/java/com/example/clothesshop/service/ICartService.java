package com.example.clothesshop.service;

import com.example.clothesshop.dto.CartDTO;
import com.example.clothesshop.dto.request.CartRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICartService {
    Page<CartDTO> findAllPageable(Pageable pageable);

    List<CartDTO> findAll(Sort sort);

    CartDTO findById(Long id);

    CartDTO save(CartRequest dto);

    String delete(Long[] ids);

    List<CartDTO> deleteCartByUserId(Long user_id);

    Page<CartDTO> findByUserId(Long user_id, Pageable pageable);

    List<CartDTO> findByUserId(Long user_id);

    List<CartDTO> deleteCartByUsername(String username);

    Page<CartDTO> findByUsername(String username, Pageable pageable);

    List<CartDTO> findByUsername(String username);

    List<CartDTO> deleteCartByEmail(String email);

    Page<CartDTO> findByEmail(String email, Pageable pageable);

    List<CartDTO> findByEmail(String email);
}
