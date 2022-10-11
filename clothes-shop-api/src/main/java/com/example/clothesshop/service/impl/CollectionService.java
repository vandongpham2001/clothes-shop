package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.CollectionConverter;
import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.dto.request.CollectionRequest;
import com.example.clothesshop.entity.CollectionEntity;
import com.example.clothesshop.entity.ProductEntity;
import com.example.clothesshop.repository.CollectionRepository;
import com.example.clothesshop.repository.ProductRepository;
import com.example.clothesshop.service.ICollectionService;
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
public class CollectionService implements ICollectionService {
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CollectionConverter collectionConverter;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Page<CollectionDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<CollectionDTO> results;
        Page<CollectionEntity> entities;
        if (status != null) {
            entities = collectionRepository.findByStatus(status, pageable);
        } else {
            entities = collectionRepository.findAll(pageable);
        }
        results = collectionConverter.mapEntityPageIntoDtoPage(entities, CollectionDTO.class);
        return results;
    }

    @Override
    public List<CollectionDTO> findAll(Integer status, Sort sort) {
        List<CollectionDTO> results;
        Iterable<CollectionEntity> entities;
        if (status != null) {
            entities = collectionRepository.findByStatus(status, sort);
        } else {
            entities = collectionRepository.findAll(sort);
        }
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), CollectionDTO.class);
        return results;
    }

    @Override
    public CollectionDTO save(CollectionRequest dto) {
        CollectionEntity entity;
        if (dto.getId() != null) {
            CollectionEntity old_entity = collectionRepository.findById(dto.getId()).get();
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
            entity = collectionConverter.toEntity(dto, old_entity);
        }
        else {
            entity = collectionConverter.toEntity(dto);
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
        return collectionConverter.toDTO(collectionRepository.save(entity));
    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            CollectionEntity exists = collectionRepository.findById(id).get();
            if (exists != null) {
//                collectionRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                collectionRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public CollectionDTO findById(long id) {
        CollectionEntity entity = collectionRepository.findById(id).get();
        return collectionConverter.toDTO(entity);
    }

    @Override
    public CollectionDTO addProductToCollection(Long collection_id, Long[] product_id) {
        CollectionEntity collection = collectionRepository.findById(collection_id).get();
        for (Long item : product_id){
            ProductEntity product = productRepository.findById(item).get();
            collection.addProduct(product);
        }
        CollectionEntity savedCollection = collectionRepository.save(collection);
        return collectionConverter.toDTO(savedCollection);
    }

    @Override
    public CollectionDTO removeProductFromCollection(Long collection_id, Long[] product_id) {
        CollectionEntity collection = collectionRepository.findById(collection_id).get();
        for (Long item : product_id){
            collection.removeProduct(item);
        }
        CollectionEntity savedCollection = collectionRepository.save(collection);
        return collectionConverter.toDTO(savedCollection);
    }
}
