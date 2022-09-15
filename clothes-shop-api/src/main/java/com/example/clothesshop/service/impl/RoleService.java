package com.example.clothesshop.service.impl;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.RoleConverter;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.entity.RoleEntity;
import com.example.clothesshop.repository.RoleRepository;
import com.example.clothesshop.service.IRoleService;
import com.example.clothesshop.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;

    @Override
    public Page<RoleDTO> findAllPageable(Pageable pageable) {
        Page<RoleDTO> results;
        Page<RoleEntity> entities;
        entities = roleRepository.findAll(pageable);
        results = roleConverter.mapEntityPageIntoDtoPage(entities, RoleDTO.class);
        return results;
    }

    @Override
    public List<RoleDTO> findAll(Sort sort) {
        List<RoleDTO> results;
        Iterable<RoleEntity> entities;
        entities = roleRepository.findAll(sort);
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), RoleDTO.class);
        return results;
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        if (dto.getId() != null) {
            RoleEntity old_entity = roleRepository.findById(dto.getId()).get();
            entity = roleConverter.toEntity(dto, old_entity);
        } else {
            entity = roleConverter.toEntity(dto);
        }
        log.info("Saving new role {} to the database", dto.getName());
        return roleConverter.toDTO(roleRepository.save(entity));
    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            RoleEntity exists = roleRepository.findById(id).get();
            if (exists != null) {
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                roleRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public RoleDTO findById(long id) {
        Optional<RoleEntity> entity = roleRepository.findById(id);
        return roleConverter.toDTO(entity.get());
    }
}
