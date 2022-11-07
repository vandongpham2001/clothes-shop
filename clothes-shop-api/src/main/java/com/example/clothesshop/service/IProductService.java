package com.example.clothesshop.service;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.dto.request.FilterRequest;
import com.example.clothesshop.dto.request.ProductRequest;
import com.example.clothesshop.dto.response.DetailProductResponse;
import com.example.clothesshop.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.math.BigDecimal;
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

    Page<ProductResponse> findAllPageableInWeb(Pageable pageable);

    List<ProductResponse> findAllInWeb(Sort sort);

    Page<ProductResponse> findByCategoryPageableInWeb(Pageable pageable, Long category_id);

    List<ProductResponse> findByCategoryInWeb(Sort sort, Long category_id);

    Page<ProductResponse> findByPromotionPageableInWeb(Pageable pageable, Long promotion_id);

    List<ProductResponse> findByPromotionInWeb(Sort sort, Long promotion_id);

    Page<ProductResponse> findByCollectionPageableInWeb(Pageable pageable, Long collection_id);

    List<ProductResponse> findByCollectionInWeb(Sort sort, Long collection_id);

    Page<ProductResponse> findByFilterPageableInWeb(Pageable pageable, FilterRequest filter);

    List<ProductResponse> findByFilterInWeb(Sort sort, FilterRequest filter);

    Page<ProductResponse> findByKeywordPageableInWeb(Pageable pageable, String keyword);

    List<ProductResponse> findByKeywordInWeb(Sort sort, String keyword);

    DetailProductResponse findDetailById(Long id);

    BigDecimal findMaxPriceProduct();

    ProductDTO save(ProductRequest dto);

    String delete(long[] ids);

    ProductDTO findById(long id);
}
