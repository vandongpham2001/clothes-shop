package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.SizeConverter;
import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.entity.SizeEntity;
import com.example.clothesshop.repository.SizeRepository;
import com.example.clothesshop.service.ISizeService;
import com.example.clothesshop.util.ObjectMapperUtil;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SizeService implements ISizeService {

    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    SizeConverter sizeConverter;

    @Override
    public Page<SizeDTO> findAllPageable(Pageable pageable) {
        Page<SizeDTO> results;
        Page<SizeEntity> entities;
        entities = sizeRepository.findAll(pageable);
        results = sizeConverter.mapEntityPageIntoDtoPage(entities, SizeDTO.class);
        return results;
    }

    @Override
    public List<SizeDTO> findAll(Sort sort) {
        List<SizeDTO> results;
        Iterable<SizeEntity> entities;
        entities = sizeRepository.findAll(sort);
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), SizeDTO.class);
        return results;
    }

    @Override
    public SizeDTO save(SizeDTO dto) throws IOException {
        SizeEntity entity;
        if (dto.getId() != null) {
            SizeEntity old_entity = sizeRepository.findById(dto.getId()).get();
            entity = sizeConverter.toEntity(dto, old_entity);
        } else {
            entity = sizeConverter.toEntity(dto);
        }
        return sizeConverter.toDTO(sizeRepository.save(entity));
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            boolean exists = sizeRepository.existsById(id);
            if (exists) {
                sizeRepository.deleteById(id);
            }
        }
    }
}
