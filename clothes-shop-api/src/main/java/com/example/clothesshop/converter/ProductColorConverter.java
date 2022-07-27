package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorDTO;
import com.example.clothesshop.entity.ProductColorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductColorConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductColorDTO toDTO(ProductColorEntity entity){
        ProductColorDTO dto = modelMapper.map(entity, ProductColorDTO.class);
        return dto;
    }

    public ProductColorEntity toEntity(ProductColorDTO dto){
        ProductColorEntity entity = modelMapper.map(dto, ProductColorEntity.class);
        return entity;
    }

    public ProductColorEntity toEntity(ProductColorDTO dto, ProductColorEntity entity){
        entity = modelMapper.map(dto, ProductColorEntity.class);
        return entity;
    }
}
