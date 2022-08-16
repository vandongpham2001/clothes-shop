package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.ProductConverter;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.entity.ProductEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.repository.ProductRepository;
import com.example.clothesshop.service.IProductService;
import com.example.clothesshop.util.CloudinaryUtil;
import com.example.clothesshop.util.ObjectMapperUtil;
import com.example.clothesshop.util.SlugUtil;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    Cloudinary cloudinary;

    @Override
    public Page<ProductDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<ProductDTO> results;
        Page<ProductEntity> entities;
        if (status!=null) {
            entities = productRepository.findByStatus(status, pageable);
        } else {
            entities = productRepository.findAll(pageable);
        }
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductDTO.class);
        return results;
    }

    public List<ProductDTO> findAll(Integer status, Sort sort) {
        List<ProductDTO> results = new ArrayList<>();
        Iterable<ProductEntity> entities = new ArrayList<>();
        if (status!=null) {
            entities = productRepository.findByStatus(status, sort);
        } else {
            entities = productRepository.findAll(sort);
        }
//        Iterator<ProductEntity> iterator = entities.iterator();
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
//    @Transactional
    public ProductDTO save(ProductDTO dto) throws IOException {
        ProductEntity entity = new ProductEntity();
        if (dto.getName() != null) {
            dto.setSlug(SlugUtil.toSlug(dto.getName()));
        }
        if (dto.getFile() != null) {
            String img = CloudinaryUtil.upload(cloudinary, dto.getFile()[0]);
            dto.setImage(img);
//            for (MultipartFile file : dto.getFile()){
//                String img = CloudinaryUtil.upload(cloudinary, file);
//            }
        }
        if (dto.getId() != null) {
            ProductEntity old_entity = productRepository.findById(dto.getId()).get();
            String file_name = CloudinaryUtil.getNameImgFromUrlCloudinary(old_entity.getImage());
            System.out.println(file_name);
            String destroy = CloudinaryUtil.destroy(cloudinary, file_name);
            System.out.println(destroy);
            entity = productConverter.toEntity(dto, old_entity);
        }
        else {
            entity = productConverter.toEntity(dto);
        }
        CategoryEntity category = categoryRepository.findById(dto.getCategory_id()).get();
        entity.setCategory(category);
        return productConverter.toDTO(productRepository.save(entity));
    }

    @Override
//    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            ProductEntity exists = productRepository.findById(id).get();
            if (exists!=null) {
//                productRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                productRepository.save(exists);
            }
        }
    }
}
