package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.dto.request.ProductRequest;
import com.example.clothesshop.entity.ProductEntity;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends AbstractConverter {

    public ProductDTO toDTO(ProductEntity entity) {
        //        dto.setCategory_id(entity.getCategory().getId());
        return modelMapper.map(entity, ProductDTO.class);
    }

    public ProductEntity toEntity(ProductRequest dto) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        modelMapper.addMappings(new PropertyMap<ProductDTO, ProductEntity>() {
//            @Override
//            protected void configure() {
//                skip().setProduct_color(null);
//            }
//        });
//        dto.setProduct_color(null);
        return modelMapper.map(dto, ProductEntity.class);
    }

    public ProductEntity toEntity(ProductRequest dto, ProductEntity entity) {
        modelMapper.map(dto, entity);
        return entity;
    }
}
