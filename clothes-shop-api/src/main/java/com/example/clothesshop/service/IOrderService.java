package com.example.clothesshop.service;

import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IOrderService {
    Page<OrderDTO> findAllPageable(Integer status, Pageable pageable);

    List<OrderDTO> findAll(Integer status, Sort sort);

    Page<OrderDTO> findPageableByUsername(String username, Integer status, Pageable pageable);

    List<OrderDTO> findByUsername(String username, Integer status, Sort sort);

    OrderDTO findById(Long id);

    OrderDTO save(OrderRequest dto);

    String delete(Long[] ids);
}
