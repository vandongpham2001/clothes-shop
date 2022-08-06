package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorImageDTO;
import com.example.clothesshop.entity.ProductColorImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductColorImageConverter extends AbstractConverter {

    public ProductColorImageDTO toDTO(ProductColorImageEntity entity){
        ProductColorImageDTO dto = modelMapper.map(entity, ProductColorImageDTO.class);
        return dto;
    }

    public ProductColorImageEntity toEntity(ProductColorImageDTO dto){
        ProductColorImageEntity entity = modelMapper.map(dto, ProductColorImageEntity.class);
        return entity;
    }

    public ProductColorImageEntity toEntity(ProductColorImageDTO dto, ProductColorImageEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
