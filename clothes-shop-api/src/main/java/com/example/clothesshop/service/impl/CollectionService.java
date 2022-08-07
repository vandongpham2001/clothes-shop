package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.CollectionConverter;
import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.entity.CollectionEntity;
import com.example.clothesshop.repository.CollectionRepository;
import com.example.clothesshop.service.ICollectionService;
import com.example.clothesshop.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionService implements ICollectionService {
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    CollectionConverter collectionConverter;
    @Override
    public List<CollectionDTO> findAll(Integer status) {
        List<CollectionDTO> results;
        List<CollectionEntity> entities = collectionRepository.findByStatus(status);
        results = ObjectMapperUtil.mapAll(entities, CollectionDTO.class);
        return results;
    }
}
