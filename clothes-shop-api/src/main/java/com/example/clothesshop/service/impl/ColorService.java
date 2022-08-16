package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.ColorConverter;
import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.entity.ColorEntity;
import com.example.clothesshop.repository.ColorRepository;
import com.example.clothesshop.service.IColorService;
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
public class ColorService implements IColorService {
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    ColorConverter colorConverter;
    @Override
    public Page<ColorDTO> findAllPageable(Pageable pageable) {
        Page<ColorDTO> results;
        Page<ColorEntity> entities;
        entities = colorRepository.findAll(pageable);
        results = colorConverter.mapEntityPageIntoDtoPage(entities, ColorDTO.class);
        return results;
    }

    @Override
    public List<ColorDTO> findAll(Sort sort) {
        List<ColorDTO> results;
        Iterable<ColorEntity> entities;
        entities = colorRepository.findAll(sort);
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), ColorDTO.class);
        return results;
    }

    @Override
    public ColorDTO save(ColorDTO dto) throws IOException {
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
    public void delete(long[] ids) {
        for (long id : ids) {
            boolean exists = colorRepository.existsById(id);
            if (exists) {
                colorRepository.deleteById(id);
            }
        }
    }
}
