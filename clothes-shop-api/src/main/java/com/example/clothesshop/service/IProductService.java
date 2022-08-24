package com.example.clothesshop.service;

import com.example.clothesshop.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Page<ProductDTO> findAllPageable(Integer status, Pageable pageable);

    List<ProductDTO> findAll(Integer status, Sort sort);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(ProductDTO dto);

    void delete(long[] ids);

    ProductDTO detail(ProductDTO dto);
}
