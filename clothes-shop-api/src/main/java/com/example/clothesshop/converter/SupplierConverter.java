package com.example.clothesshop.converter;

import com.example.clothesshop.dto.SupplierDTO;
import com.example.clothesshop.entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierConverter {

    @Autowired
    private ModelMapper modelMapper;

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
