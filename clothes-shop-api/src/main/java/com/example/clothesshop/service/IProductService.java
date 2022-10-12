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

    Page<ProductDTO> findPageableByCategoryId(Long category_id, Integer status, Pageable pageable);

    List<ProductDTO> findByCategoryId(Long category_id, Integer status, Sort sort);

    Page<ProductDTO> findPageableByPromotionId(Long promotion_id, Integer status, Pageable pageable);

    List<ProductDTO> findByPromotionId(Long promotion_id, Integer status, Sort sort);

    Page<ProductDTO> findPageableByCollectionId(Long collection_id, Integer status, Pageable pageable);

    List<ProductDTO> findByCollectionId(Long collection_id, Integer status, Sort sort);

    ProductDTO save(ProductRequest dto);

    String delete(long[] ids);

    ProductDTO findById(long id);
}
