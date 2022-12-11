package com.example.clothesshop.service.impl;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.OrderConverter;
import com.example.clothesshop.converter.OrderDetailConverter;
import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.dto.OrderDetailDTO;
import com.example.clothesshop.dto.request.OrderDetailRequest;
import com.example.clothesshop.dto.request.OrderRequest;
import com.example.clothesshop.entity.OrderDetailEntity;
import com.example.clothesshop.entity.OrderEntity;
import com.example.clothesshop.entity.ProductColorSizeEntity;
import com.example.clothesshop.repository.OrderDetailRepository;
import com.example.clothesshop.repository.OrderRepository;
import com.example.clothesshop.repository.ProductColorSizeRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.service.IOrderService;
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
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private OrderDetailConverter orderDetailConverter;

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
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), OrderDTO.class);
        return results;
    }

    @Override
    public Page<OrderDTO> findPageableByUsername(String username, Integer status, Pageable pageable) {
        Page<OrderDTO> results;
        Page<OrderEntity> entities;
        if (status != null) {
            entities = orderRepository.findByStatusAndUsername(username, status, pageable);
        } else {
            entities = orderRepository.findByUsername(username, pageable);
        }
        results = orderConverter.mapEntityPageIntoDtoPage(entities, OrderDTO.class);
        return results;
    }

    @Override
    public List<OrderDTO> findByUsername(String username, Integer status, Sort sort) {
        List<OrderDTO> results;
        Iterable<OrderEntity> entities;
        if (status != null) {
            entities = orderRepository.findByStatusAndUsername(username, status, sort);
        } else {
            entities = orderRepository.findByUsername(username, sort);
        }
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), OrderDTO.class);
        return results;
    }

    @Override
    public OrderDTO findById(Long id) {
        OrderEntity entity = orderRepository.findById(id).get();
        return orderConverter.toDTO(entity);
    }

    @Override
    public OrderDTO save(OrderRequest dto) {
        OrderEntity entity;
        List<OrderDetailRequest> listOrderDetailDTO = dto.getOrder_detail();
        dto.setOrder_detail(null);
        if (dto.getId() != null) {
            OrderEntity old_entity = orderRepository.findById(dto.getId()).get();
            entity = orderConverter.toEntity(dto, old_entity);
        } else {
            entity = orderConverter.toEntity(dto);
        }
        if (dto.getUser_id() != null) {
            entity.setUser(userRepository.findById(dto.getUser_id()).get());
        }
        OrderDTO savedOrder = orderConverter.toDTO(orderRepository.save(entity));
        if (listOrderDetailDTO != null){
            for (OrderDetailRequest orderDetailDTO : listOrderDetailDTO){
                orderDetailDTO.setOrder_id(savedOrder.getId());
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                Integer max_quantity = productColorSizeRepository.findById(orderDetailDTO.getProduct_color_size_id()).get().getQuantity();
                System.out.println("max quantity of product: "+max_quantity);
                if (max_quantity == 0){
                    System.out.println("check max quantity = 0, continue");
                    continue;
                }
                if (orderDetailDTO.getQuantity()>max_quantity){
                    orderDetailDTO.setQuantity(max_quantity);
                    System.out.println("compare order quantity with current quantity"+max_quantity);
                }
                if (orderDetailDTO.getId() != null){
                    OrderDetailEntity oldOrderDetailEntity = orderDetailRepository.findById(orderDetailDTO.getId()).get();
                    orderDetailEntity = orderDetailConverter.toEntity(orderDetailDTO, oldOrderDetailEntity);
                }
                else {
                    orderDetailEntity = orderDetailConverter.toEntity(orderDetailDTO);
                }
                if (orderDetailDTO.getOrder_id() != null) {
                    orderDetailEntity.setOrder(orderRepository.findById(orderDetailDTO.getOrder_id()).get());
                }
                if (orderDetailDTO.getProduct_color_size_id() != null) {
                    orderDetailEntity.setProduct_color_size(productColorSizeRepository.findById(orderDetailDTO.getProduct_color_size_id()).get());
                }
                OrderDetailDTO savedOrderDetail = orderDetailConverter.toDTO(orderDetailRepository.save(orderDetailEntity));
                if (orderDetailEntity.getProduct_color_size().getId()!=null) {
                    System.out.println("check id of product color size");
                    ProductColorSizeEntity productColorSizeEntity = productColorSizeRepository.findById(orderDetailEntity.getProduct_color_size().getId()).get();
                    if (savedOrderDetail.getQuantity()!=null){
                        Integer current_quantity = productColorSizeEntity.getQuantity();
                        System.out.println("current quantity: "+current_quantity);
                        Integer sold_quantity = savedOrderDetail.getQuantity();
                        productColorSizeEntity.setQuantity(current_quantity-sold_quantity);
                        System.out.println("sold quantity: "+sold_quantity);
                        ProductColorSizeEntity updatedProductColorSize = productColorSizeRepository.save(productColorSizeEntity);
                    }
                }
            }
        }
        OrderEntity finalEntity = orderRepository.findById(savedOrder.getId()).get();
        finalEntity.setOrder_detail(orderDetailRepository.findByOrderId(finalEntity.getId()));
        return orderConverter.toDTO(finalEntity);
    }

    @Override
    public String delete(Long[] ids) {
        for (Long id : ids) {
            OrderEntity exists = orderRepository.findById(id).get();
            if (exists != null) {
//                orderRepository.deleteById(id);
                exists.setStatus(SystemConstant.ORDER_CANCEL_STATUS);
                orderRepository.save(exists);
            }
        }
        return "deleted";
    }
}
