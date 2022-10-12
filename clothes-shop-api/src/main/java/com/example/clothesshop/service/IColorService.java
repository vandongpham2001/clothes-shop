package com.example.clothesshop.service;

import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.dto.request.ColorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface IColorService {
    Page<ColorDTO> findAllPageable(Integer status, Pageable pageable);

    List<ColorDTO> findAll(Integer status, Sort sort);

    Page<ColorDTO> findPageableByProductId(Long product_id, Integer status, Pageable pageable);

    List<ColorDTO> findByProductId(Long product_id, Integer status, Sort sort);

    ColorDTO save(ColorRequest dto);

    String delete(long[] ids);

    ColorDTO findById(long id);
}
