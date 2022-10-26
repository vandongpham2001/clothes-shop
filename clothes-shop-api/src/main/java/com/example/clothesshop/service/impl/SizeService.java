package com.example.clothesshop.service.impl;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.SizeConverter;
import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.dto.request.SizeRequest;
import com.example.clothesshop.entity.ColorEntity;
import com.example.clothesshop.entity.SizeEntity;
import com.example.clothesshop.repository.SizeRepository;
import com.example.clothesshop.service.ISizeService;
import com.example.clothesshop.utils.ObjectMapperUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizeService implements ISizeService {

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private SizeConverter sizeConverter;

    @Override
    public Page<SizeDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<SizeDTO> results;
        Page<SizeEntity> entities;
        if (status!=null) {
            entities = sizeRepository.findByStatus(status, pageable);
        } else {
            entities = sizeRepository.findAll(pageable);
        }
        results = sizeConverter.mapEntityPageIntoDtoPage(entities, SizeDTO.class);
        return results;
    }

    @Override
    public List<SizeDTO> findAll(Integer status, Sort sort) {
        List<SizeDTO> results;
        Iterable<SizeEntity> entities;
        if (status!=null) {
            entities = sizeRepository.findByStatus(status, sort);
        } else {
            entities = sizeRepository.findAll(sort);
        }
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), SizeDTO.class);
        return results;
    }

    @Override
    public Page<SizeDTO> findPageableByProductId(Long product_id, Integer status, Pageable pageable) {
        Page<SizeDTO> results;
        Page<SizeEntity> entities;
        entities = sizeRepository.findSizeEntitiesByStatusAndProductsId(status, product_id, pageable);
        results = sizeConverter.mapEntityPageIntoDtoPage(entities, SizeDTO.class);
        return results;
    }

    @Override
    public List<SizeDTO> findByProductId(Long product_id, Integer status, Sort sort) {
        List<SizeDTO> results;
        Iterable<SizeEntity> entities;
        entities = sizeRepository.findSizeEntitiesByStatusAndProductsId(status, product_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), SizeDTO.class);
        return results;
    }

    @Override
    public SizeDTO save(SizeRequest dto){
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
    public String delete(long[] ids) {
        for (long id : ids) {
            SizeEntity exists = sizeRepository.findById(id).get();
            if (exists != null) {
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                sizeRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public SizeDTO findById(long id) {
        SizeEntity entity = sizeRepository.findById(id).get();
        return sizeConverter.toDTO(entity);
    }
}
