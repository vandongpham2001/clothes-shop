package com.example.clothesshop.converter;

import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter {

    public RoleDTO toDTO(RoleEntity entity){
        RoleDTO dto = modelMapper.map(entity, RoleDTO.class);
        return dto;
    }

    public RoleEntity toEntity(RoleDTO dto){
        RoleEntity entity = modelMapper.map(dto, RoleEntity.class);
        return entity;
    }

    public RoleEntity toEntity(RoleDTO dto, RoleEntity entity){
        modelMapper.map(dto, entity);
        return entity;
    }
}
