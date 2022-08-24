package com.example.clothesshop.service;

import com.example.clothesshop.dto.PromotionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface IPromotionService {
    Page<PromotionDTO> findAllPageable(Integer status, Pageable pageable);

    List<PromotionDTO> findAll(Integer status, Sort sort);

    PromotionDTO save(PromotionDTO dto);

    void delete(long[] ids);
}
