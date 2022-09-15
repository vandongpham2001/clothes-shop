package com.example.clothesshop.service;

import com.example.clothesshop.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IRoleService {
    Page<RoleDTO> findAllPageable(Pageable pageable);

    List<RoleDTO> findAll(Sort sort);

    RoleDTO save(RoleDTO dto);

    String delete(long[] ids);

    RoleDTO findById(long id);
}
