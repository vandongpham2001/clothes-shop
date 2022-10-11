package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.PromotionConverter;
import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.dto.request.PromotionRequest;
import com.example.clothesshop.entity.ProductEntity;
import com.example.clothesshop.entity.PromotionEntity;
import com.example.clothesshop.repository.ProductRepository;
import com.example.clothesshop.repository.PromotionRepository;
import com.example.clothesshop.service.IPromotionService;
import com.example.clothesshop.utils.CloudinaryUtils;
import com.example.clothesshop.utils.ObjectMapperUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class PromotionService implements IPromotionService {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionConverter promotionConverter;
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public Page<PromotionDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<PromotionDTO> results;
        Page<PromotionEntity> entities;
        if (status != null) {
            entities = promotionRepository.findByStatus(status, pageable);
        } else {
            entities = promotionRepository.findAll(pageable);
        }
        results = promotionConverter.mapEntityPageIntoDtoPage(entities, PromotionDTO.class);
        return results;
    }

    @Override
    public List<PromotionDTO> findAll(Integer status, Sort sort) {
        List<PromotionDTO> results;
        Iterable<PromotionEntity> entities;
        if (status != null) {
            entities = promotionRepository.findByStatus(status, sort);
        } else {
            entities = promotionRepository.findAll(sort);
        }
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), PromotionDTO.class);
        return results;
    }

    @Override
    public PromotionDTO save(PromotionRequest dto) {
        PromotionEntity entity;
        if (dto.getId() != null) {
            PromotionEntity old_entity = promotionRepository.findById(dto.getId()).get();
            if (old_entity.getMobile_banner() != null  && dto.getMobile_banner_file() != null) {
                String file_name = CloudinaryUtils.getNameImgFromUrlCloudinary(old_entity.getMobile_banner());
                try {
                    String destroy = CloudinaryUtils.destroy(cloudinary, file_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (old_entity.getPc_banner() != null  && dto.getPc_banner_file() != null) {
                String file_name = CloudinaryUtils.getNameImgFromUrlCloudinary(old_entity.getPc_banner());
                try {
                    String destroy = CloudinaryUtils.destroy(cloudinary, file_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            entity = promotionConverter.toEntity(dto, old_entity);
        }
        else {
            entity = promotionConverter.toEntity(dto);
        }
        if (dto.getMobile_banner_file() != null) {
            try {
                String img = CloudinaryUtils.upload(cloudinary, dto.getMobile_banner_file());
                entity.setMobile_banner(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (dto.getPc_banner_file() != null) {
            try {
                String img = CloudinaryUtils.upload(cloudinary, dto.getPc_banner_file());
                entity.setPc_banner(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return promotionConverter.toDTO(promotionRepository.save(entity));
    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            PromotionEntity exists = promotionRepository.findById(id).get();
            if (exists != null) {
//                promotionRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                promotionRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public PromotionDTO findById(long id) {
        PromotionEntity entity = promotionRepository.findById(id).get();
        return promotionConverter.toDTO(entity);
    }

    @Override
    public PromotionDTO addProductToPromotion(Long promotion_id, Long[] product_id) {
        PromotionEntity promotion = promotionRepository.findById(promotion_id).get();
        for (Long item : product_id){
            ProductEntity product = productRepository.findById(item).get();
            promotion.addProduct(product);
        }
        PromotionEntity savedPromotion = promotionRepository.save(promotion);
        return promotionConverter.toDTO(savedPromotion);
    }

    @Override
    public PromotionDTO removeProductFromPromotion(Long promotion_id, Long[] product_id) {
        PromotionEntity promotion = promotionRepository.findById(promotion_id).get();
        for (Long item : product_id){
            promotion.removeProduct(item);
        }
        PromotionEntity savedPromotion = promotionRepository.save(promotion);
        return promotionConverter.toDTO(savedPromotion);
    }
}
