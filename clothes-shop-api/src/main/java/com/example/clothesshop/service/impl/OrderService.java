package com.example.clothesshop.service.impl;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.OrderConverter;
import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.entity.OrderEntity;
import com.example.clothesshop.repository.OrderRepository;
import com.example.clothesshop.service.IOrderService;
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
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public Page<OrderDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<OrderDTO> results;
        Page<OrderEntity> entities;
        if (status != null) {
            entities = orderRepository.findByStatus(status, pageable);
        } else {
            entities = orderRepository.findAll(pageable);
        }
        results = orderConverter.mapEntityPageIntoDtoPage(entities, OrderDTO.class);
        return results;
    }

    @Override
    public List<OrderDTO> findAll(Integer status, Sort sort) {
        List<OrderDTO> results;
        Iterable<OrderEntity> entities;
        if (status != null) {
            entities = orderRepository.findByStatus(status, sort);
        } else {
            entities = orderRepository.findAll(sort);
        }
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), OrderDTO.class);
        return results;
    }

    @Override
    public OrderDTO findById(long id) {
        OrderEntity entity = orderRepository.findById(id).get();
        return orderConverter.toDTO(entity);
    }

    @Override
    public OrderDTO save(OrderDTO dto) {
        OrderEntity entity;
        if (dto.getId() != null) {
            OrderEntity old_entity = orderRepository.findById(dto.getId()).get();
            entity = orderConverter.toEntity(dto, old_entity);
        } else {
            entity = orderConverter.toEntity(dto);
        }
//        if (dto.getCategory_id() != null) {
//            entity.setCategory(categoryRepository.findById(dto.getCategory_id()).get());
//        }
        return orderConverter.toDTO(orderRepository.save(entity));
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            OrderEntity exists = orderRepository.findById(id).get();
            if (exists != null) {
//                orderRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                orderRepository.save(exists);
            }
        }
    }
}
