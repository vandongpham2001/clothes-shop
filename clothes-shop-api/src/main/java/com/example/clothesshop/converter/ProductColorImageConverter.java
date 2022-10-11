package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorImageDTO;
import com.example.clothesshop.dto.request.ProductColorImageRequest;
import com.example.clothesshop.entity.ProductColorImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductColorImageConverter extends AbstractConverter {

    public ProductColorImageDTO toDTO(ProductColorImageEntity entity){
        return modelMapper.map(entity, ProductColorImageDTO.class);
    }

    public ProductColorImageEntity toEntity(ProductColorImageRequest dto){
        return modelMapper.map(dto, ProductColorImageEntity.class);
    }

    public ProductColorImageEntity toEntity(ProductColorImageRequest dto, ProductColorImageEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
