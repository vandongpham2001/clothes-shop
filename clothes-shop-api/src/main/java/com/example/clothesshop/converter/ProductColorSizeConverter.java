package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorSizeDTO;
import com.example.clothesshop.entity.ProductColorSizeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductColorSizeConverter extends AbstractConverter {

    public ProductColorSizeDTO toDTO(ProductColorSizeEntity entity){
        ProductColorSizeDTO dto = modelMapper.map(entity, ProductColorSizeDTO.class);
        return dto;
    }

    public ProductColorSizeEntity toEntity(ProductColorSizeDTO dto){
        ProductColorSizeEntity entity = modelMapper.map(dto, ProductColorSizeEntity.class);
        return entity;
    }

    public ProductColorSizeEntity toEntity(ProductColorSizeDTO dto, ProductColorSizeEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
