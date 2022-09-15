package com.example.clothesshop.service;

import com.example.clothesshop.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IOrderService {
    Page<OrderDTO> findAllPageable(Integer status, Pageable pageable);

    List<OrderDTO> findAll(Integer status, Sort sort);

    OrderDTO findById(long id);

    OrderDTO save(OrderDTO dto);

    String delete(long[] ids);
}