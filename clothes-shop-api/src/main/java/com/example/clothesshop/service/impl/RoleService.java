package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.RoleConverter;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.entity.RoleEntity;
import com.example.clothesshop.repository.RoleRepository;
import com.example.clothesshop.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleConverter roleConverter;

    @Override
    public RoleDTO save(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        if (dto.getId() != null){
            RoleEntity old_entity = roleRepository.findById(dto.getId()).get();
            entity = roleConverter.toEntity(dto, old_entity);
        } else {
            entity = roleConverter.toEntity(dto);
        }
        log.info("Saving new role {} to the database", dto.getName());
        return roleConverter.toDTO(roleRepository.save(entity));
    }
}
