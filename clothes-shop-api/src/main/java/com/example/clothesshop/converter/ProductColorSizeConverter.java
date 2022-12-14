package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorSizeDTO;
import com.example.clothesshop.dto.request.ProductColorSizeRequest;
import com.example.clothesshop.entity.ProductColorSizeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductColorSizeConverter extends AbstractConverter {

    public ProductColorSizeDTO toDTO(ProductColorSizeEntity entity){
        return modelMapper.map(entity, ProductColorSizeDTO.class);
    }

    public ProductColorSizeEntity toEntity(ProductColorSizeRequest dto){
        return modelMapper.map(dto, ProductColorSizeEntity.class);
    }

    public ProductColorSizeEntity toEntity(ProductColorSizeRequest dto, ProductColorSizeEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
