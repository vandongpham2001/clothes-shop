package com.example.clothesshop.service;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.dto.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    Page<ProductDTO> findAllPageable(Integer status, Pageable pageable);

    List<ProductDTO> findAll(Integer status, Sort sort);

    ProductDTO save(ProductRequest dto);

    String delete(long[] ids);

    ProductDTO findById(long id);
}
