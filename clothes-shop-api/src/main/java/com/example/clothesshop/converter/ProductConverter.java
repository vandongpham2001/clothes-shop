package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO toDTO(ProductEntity entity){
        ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
        return dto;
    }

    public ProductEntity toEntity(ProductDTO dto){
        ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
        return entity;
    }

    public ProductEntity toEntity(ProductDTO dto, ProductEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
