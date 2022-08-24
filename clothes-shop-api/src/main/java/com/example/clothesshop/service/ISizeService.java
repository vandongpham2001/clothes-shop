package com.example.clothesshop.service;

import com.example.clothesshop.dto.SizeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface ISizeService {
    Page<SizeDTO> findAllPageable(Pageable pageable);

    List<SizeDTO> findAll(Sort sort);

    SizeDTO save(SizeDTO dto);

    void delete(long[] ids);
}
