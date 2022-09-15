package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.CartConverter;
import com.example.clothesshop.dto.CartDTO;
import com.example.clothesshop.entity.CartEntity;
import com.example.clothesshop.entity.UserEntity;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.repository.ProductColorSizeRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.service.ICartService;
import com.example.clothesshop.util.ObjectMapperUtil;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;
    @Autowired
    private CartConverter cartConverter;

    @Override
    public Page<CartDTO> findAllPageable(Pageable pageable) {
        Page<CartDTO> results;
        Page<CartEntity> entities;
        entities = cartRepository.findAll(pageable);
        results = cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
        return results;
    }

    @Override
    public List<CartDTO> findAll(Sort sort) {
        List<CartDTO> results;
        Iterable<CartEntity> entities;
        entities = cartRepository.findAll(sort);
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), CartDTO.class);
        return results;
    }

    @Override
    public CartDTO findById(long id) {
        CartEntity entity = cartRepository.findById(id).get();
        return cartConverter.toDTO(entity);
    }

    @Override
    public CartDTO save(CartDTO dto) {
        CartEntity entity;
        if (dto.getId() != null) {
            CartEntity old_entity = cartRepository.findById(dto.getId()).get();
            entity = cartConverter.toEntity(dto, old_entity);
        } else {
            entity = cartConverter.toEntity(dto);
        }
        if (dto.getUser_id() != null) {
            entity.setUser(userRepository.findById(dto.getUser_id()).get());
        }
        if (dto.getProduct_color_size_id() != null){
            entity.setProduct_color_size(productColorSizeRepository.findById(dto.getProduct_color_size_id()).get());
        }
        return cartConverter.toDTO(cartRepository.save(entity));
    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            boolean exists = cartRepository.existsById(id);
            if (exists) {
                cartRepository.deleteById(id);
            }
        }
        return "deleted";
    }

    @Override
    public List<CartDTO> deleteCartByUserId(long user_id) {
        UserEntity user = userRepository.findById(user_id).get();
        if (user != null){
            cartRepository.deleteByUserId(user_id);
        }
        return ObjectMapperUtil.mapAll(cartRepository.findAllByUserId(user_id), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findAllByUserId(long user_id, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByUserId(user_id, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findAllByUserId(long user_id) {
        List<CartEntity> entities = cartRepository.findAllByUserId(user_id);
        return ObjectMapperUtil.mapAll(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> deleteCartByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null){
            cartRepository.deleteByUsername(username);
        }
        return ObjectMapperUtil.mapAll(cartRepository.findAllByUsername(username), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findAllByUsername(String username, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByUsername(username, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findAllByUsername(String username) {
        List<CartEntity> entities = cartRepository.findAllByUsername(username);
        return ObjectMapperUtil.mapAll(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> deleteCartByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null){
            cartRepository.deleteByEmail(email);
        }
        return ObjectMapperUtil.mapAll(cartRepository.findAllByEmail(email), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findAllByEmail(String email, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByEmail(email, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findAllByEmail(String email) {
        List<CartEntity> entities = cartRepository.findAllByEmail(email);
        return ObjectMapperUtil.mapAll(entities, CartDTO.class);
    }
}
