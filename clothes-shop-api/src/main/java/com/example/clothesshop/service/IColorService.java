package com.example.clothesshop.service;

import com.example.clothesshop.dto.ColorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface IColorService {
    Page<ColorDTO> findAllPageable(Pageable pageable);

    List<ColorDTO> findAll(Sort sort);

    ColorDTO save(ColorDTO dto);

    void delete(long[] ids);
}
