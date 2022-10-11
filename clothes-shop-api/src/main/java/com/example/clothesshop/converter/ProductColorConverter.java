package com.example.clothesshop.converter;

import com.example.clothesshop.dto.ProductColorDTO;
import com.example.clothesshop.dto.request.ProductColorRequest;
import com.example.clothesshop.entity.ProductColorEntity;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProductColorConverter extends AbstractConverter {

    public ProductColorDTO toDTO(ProductColorEntity entity){
        return modelMapper.map(entity, ProductColorDTO.class);
    }

    public ProductColorEntity toEntity(ProductColorRequest dto){
//        modelMapper.addMappings(new PropertyMap<ProductColorDTO, ProductColorEntity>() {
//            @Override
//            protected void configure() {
//                skip(destination.getProduct_color_image(), destination.getProduct_color_size());
//            }
//        });
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        modelMapper.addMappings(new PropertyMap<ProductColorDTO, ProductColorEntity>() {
//            @Override
//            protected void configure() {
//                skip().setProduct_color_image(null);
//                skip().setProduct_color_size(null);
//            }
//        });
        return modelMapper.map(dto, ProductColorEntity.class);
    }

    public ProductColorEntity toEntity(ProductColorRequest dto, ProductColorEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
