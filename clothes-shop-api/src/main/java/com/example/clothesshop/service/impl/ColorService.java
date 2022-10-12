package com.example.clothesshop.service.impl;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.ColorConverter;
import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.dto.request.ColorRequest;
import com.example.clothesshop.entity.ColorEntity;
import com.example.clothesshop.repository.ColorRepository;
import com.example.clothesshop.service.IColorService;
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
public class ColorService implements IColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ColorConverter colorConverter;

    @Override
    public Page<ColorDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<ColorDTO> results;
        Page<ColorEntity> entities;
        if (status!=null) {
            entities = colorRepository.findByStatus(status, pageable);
        } else {
            entities = colorRepository.findAll(pageable);
        }
        results = colorConverter.mapEntityPageIntoDtoPage(entities, ColorDTO.class);
        return results;
    }

    @Override
    public List<ColorDTO> findAll(Integer status, Sort sort) {
        List<ColorDTO> results;
        Iterable<ColorEntity> entities;
        if (status!=null) {
            entities = colorRepository.findByStatus(status, sort);
        } else {
            entities = colorRepository.findAll(sort);
        }
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ColorDTO.class);
        return results;
    }

    @Override
    public Page<ColorDTO> findPageableByProductId(Long product_id, Integer status, Pageable pageable) {
        Page<ColorDTO> results;
        Page<ColorEntity> entities;
        entities = colorRepository.findColorEntitiesByStatusAndProductsId(status, product_id, pageable);
        results = colorConverter.mapEntityPageIntoDtoPage(entities, ColorDTO.class);
        return results;
    }

    @Override
    public List<ColorDTO> findByProductId(Long product_id, Integer status, Sort sort) {
        List<ColorDTO> results;
        Iterable<ColorEntity> entities;
        entities = colorRepository.findColorEntitiesByStatusAndProductsId(status, product_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ColorDTO.class);
        return results;
    }

    @Override
    public ColorDTO save(ColorRequest dto) {
        ColorEntity entity;
        if (dto.getId() != null) {
            ColorEntity old_entity = colorRepository.findById(dto.getId()).get();
            entity = colorConverter.toEntity(dto, old_entity);
        } else {
            entity = colorConverter.toEntity(dto);
        }
        return colorConverter.toDTO(colorRepository.save(entity));
    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            ColorEntity exists = colorRepository.findById(id).get();
            if (exists != null) {
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                colorRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public ColorDTO findById(long id) {
        ColorEntity entity = colorRepository.findById(id).get();
        return colorConverter.toDTO(entity);
    }
}
