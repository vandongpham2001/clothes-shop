package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorImageDTO;
import com.example.clothesshop.entity.ProductColorImageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductColorImageConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductColorImageDTO toDTO(ProductColorImageEntity entity){
        ProductColorImageDTO dto = modelMapper.map(entity, ProductColorImageDTO.class);
        return dto;
    }

    public ProductColorImageEntity toEntity(ProductColorImageDTO dto){
        ProductColorImageEntity entity = modelMapper.map(dto, ProductColorImageEntity.class);
        return entity;
    }

    public ProductColorImageEntity toEntity(ProductColorImageDTO dto, ProductColorImageEntity entity){
        entity = modelMapper.map(dto, ProductColorImageEntity.class);
        return entity;
    }
}
