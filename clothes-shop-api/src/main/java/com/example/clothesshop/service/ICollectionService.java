package com.example.clothesshop.service;

import com.example.clothesshop.dto.CollectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICollectionService {

    Page<CollectionDTO> findAllPageable(Integer status, Pageable pageable);

    List<CollectionDTO> findAll(Integer status, Sort sort);

    CollectionDTO save(CollectionDTO dto);

    void delete(long[] ids);

    CollectionDTO findById(long id);

    CollectionDTO addProductToCollection(Long collection_id, Long[] product_id);

    CollectionDTO removeProductFromCollection(Long collection_id, Long[] product_id);
}
