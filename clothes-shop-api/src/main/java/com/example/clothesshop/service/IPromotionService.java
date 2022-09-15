package com.example.clothesshop.service;

import com.example.clothesshop.dto.PromotionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IPromotionService {
    Page<PromotionDTO> findAllPageable(Integer status, Pageable pageable);

    List<PromotionDTO> findAll(Integer status, Sort sort);

    PromotionDTO save(PromotionDTO dto);

    String delete(long[] ids);

    PromotionDTO findById(long id);

    PromotionDTO addProductToPromotion(Long promotion_id, Long[] product_id);

    PromotionDTO removeProductFromPromotion(Long promotion_id, Long[] product_id);
}
