package com.example.clothesshop.converter;

import com.example.clothesshop.dto.SupplierDTO;
import com.example.clothesshop.entity.SupplierEntity;
import org.springframework.stereotype.Component;

@Component
public class SupplierConverter extends AbstractConverter {

    public SupplierDTO toDTO(SupplierEntity entity){
        SupplierDTO dto = modelMapper.map(entity, SupplierDTO.class);
        return dto;
    }

    public SupplierEntity toEntity(SupplierDTO dto){
        SupplierEntity entity = modelMapper.map(dto, SupplierEntity.class);
        return entity;
    }

    public SupplierEntity toEntity(SupplierDTO dto, SupplierEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
