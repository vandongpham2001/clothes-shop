package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.CartConverter;
import com.example.clothesshop.dto.CartDTO;
import com.example.clothesshop.dto.request.CartRequest;
import com.example.clothesshop.entity.CartEntity;
import com.example.clothesshop.entity.ProductColorSizeEntity;
import com.example.clothesshop.entity.UserEntity;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.repository.ProductColorSizeRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.service.ICartService;
import com.example.clothesshop.utils.ObjectMapperUtils;
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
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), CartDTO.class);
        return results;
    }

    @Override
    public CartDTO findById(Long id) {
        CartEntity entity = cartRepository.findById(id).get();
        return cartConverter.toDTO(entity);
    }

    @Override
    public CartDTO save(CartRequest dto) {
        CartEntity entity = null;
        List<CartEntity> entities = cartRepository.findAllByUserId(dto.getUser_id());
        Integer max_quantity = productColorSizeRepository.findById(dto.getProduct_color_size_id()).get().getQuantity();
        if (max_quantity == 0){
            return null;
        }
        if (dto.getQuantity()>max_quantity){
            dto.setQuantity(max_quantity);
        }
        if (dto.getId() != null) {
            CartEntity old_entity = cartRepository.findById(dto.getId()).get();
            entity = cartConverter.toEntity(dto, old_entity);
        } else {
            if (entities.size() > 0) {
                for (CartEntity item : entities) {
                    if (item.getProduct_color_size() == productColorSizeRepository.findById(dto.getProduct_color_size_id()).get()) {
                        Integer quantity = dto.getQuantity() + item.getQuantity();
                        dto.setQuantity(quantity);
                        dto.setId(item.getId());
                        CartEntity old_entity = cartRepository.findById(dto.getId()).get();
                        entity = cartConverter.toEntity(dto, old_entity);
                        break;
                    } else {
                        entity = cartConverter.toEntity(dto);
                    }
                }
            } else {
                entity = cartConverter.toEntity(dto);
            }
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
    public String delete(Long[] ids) {
        for (Long id : ids) {
            CartEntity exists = cartRepository.findById(id).get();
            if (exists != null) {
//                cartRepository.deleteById(id);
//                exists.setUser(null);
//                exists.setProduct_color_size(null);
//                cartRepository.delete(exists);
//                CartEntity update = cartRepository.save(exists);
//                cartRepository.deleteById(id);
                cartRepository.delete(id);
            }
        }
        return "deleted";
    }

    @Override
    public List<CartDTO> deleteCartByUserId(Long user_id) {
        UserEntity user = userRepository.findById(user_id).get();
        if (user != null){
            cartRepository.deleteByUserId(user_id);
        }
        return ObjectMapperUtils.mapAll(cartRepository.findAllByUserId(user_id), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findByUserId(Long user_id, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByUserId(user_id, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findByUserId(Long user_id) {
        List<CartEntity> entities = cartRepository.findAllByUserId(user_id);
        return ObjectMapperUtils.mapAll(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> deleteCartByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null){
            cartRepository.deleteByUserId(user.getId());
        }
        return ObjectMapperUtils.mapAll(cartRepository.findAllByUsername(username), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findByUsername(String username, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByUsername(username, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findByUsername(String username) {
        List<CartEntity> entities = cartRepository.findAllByUsername(username);
        return ObjectMapperUtils.mapAll(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> deleteCartByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null){
            cartRepository.deleteByEmail(email);
        }
        return ObjectMapperUtils.mapAll(cartRepository.findAllByEmail(email), CartDTO.class);
    }

    @Override
    public Page<CartDTO> findByEmail(String email, Pageable pageable) {
        Page<CartEntity> entities = cartRepository.findAllByEmail(email, pageable);
        return cartConverter.mapEntityPageIntoDtoPage(entities, CartDTO.class);
    }

    @Override
    public List<CartDTO> findByEmail(String email) {
        List<CartEntity> entities = cartRepository.findAllByEmail(email);
        return ObjectMapperUtils.mapAll(entities, CartDTO.class);
    }
}
