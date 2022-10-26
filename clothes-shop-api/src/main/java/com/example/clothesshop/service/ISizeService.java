package com.example.clothesshop.service;

import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.dto.request.SizeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ISizeService {
    Page<SizeDTO> findAllPageable(Integer status, Pageable pageable);

    List<SizeDTO> findAll(Integer status, Sort sort);

    Page<SizeDTO> findPageableByProductId(Long product_id, Integer status, Pageable pageable);

    List<SizeDTO> findByProductId(Long product_id, Integer status, Sort sort);

    SizeDTO save(SizeRequest dto);

    String delete(long[] ids);

    SizeDTO findById(long id);
}
