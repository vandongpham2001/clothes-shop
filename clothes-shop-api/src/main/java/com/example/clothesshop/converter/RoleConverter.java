package com.example.clothesshop.converter;

import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    private ModelMapper modelMapper;

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
