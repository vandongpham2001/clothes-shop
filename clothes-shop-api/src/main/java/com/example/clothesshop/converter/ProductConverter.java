package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends AbstractConverter {

    public ProductDTO toDTO(ProductEntity entity){
        ProductDTO dto = modelMapper.map(entity, ProductDTO.class);
//        dto.setCategory_id(entity.getCategory().getId());
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
