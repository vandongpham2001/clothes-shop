package com.example.clothesshop.service;

import com.example.clothesshop.dto.SizeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface ISizeService {
    Page<SizeDTO> findAllPageable(Integer status, Pageable pageable);

    List<SizeDTO> findAll(Integer status, Sort sort);

    SizeDTO save(SizeDTO dto);

    String delete(long[] ids);

    SizeDTO findById(long id);
}
