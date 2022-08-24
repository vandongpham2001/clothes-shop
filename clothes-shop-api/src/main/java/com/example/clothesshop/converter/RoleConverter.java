package com.example.clothesshop.converter;

import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter {

    public RoleDTO toDTO(RoleEntity entity){
        return modelMapper.map(entity, RoleDTO.class);
    }

    public RoleEntity toEntity(RoleDTO dto){
        return modelMapper.map(dto, RoleEntity.class);
    }

    public RoleEntity toEntity(RoleDTO dto, RoleEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
