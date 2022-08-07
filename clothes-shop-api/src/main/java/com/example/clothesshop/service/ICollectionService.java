package com.example.clothesshop.service;

import com.example.clothesshop.dto.CollectionDTO;

import java.util.List;

public interface ICollectionService {
    List<CollectionDTO> findAll(Integer status);
}
