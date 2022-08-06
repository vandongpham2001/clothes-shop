package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.converter.CategoryConverter;
import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.service.ICategoryService;
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

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    Cloudinary cloudinary;

    @Override
    public Page<CategoryDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<CategoryDTO> results;
        Page<CategoryEntity> entities;
        if (status!=null) {
            entities = categoryRepository.findByStatus(status, pageable);
        } else {
            entities = categoryRepository.findAll(pageable);
        }
        results = categoryConverter.mapEntityPageIntoDtoPage(entities, CategoryDTO.class);
        return results;
    }

    public List<CategoryDTO> findAll(Integer status, Sort sort) {
        List<CategoryDTO> results = new ArrayList<>();
        Iterable<CategoryEntity> entities = new ArrayList<>();
        if (status!=null) {
            entities = categoryRepository.findByStatus(status, sort);
        } else {
            entities = categoryRepository.findAll(sort);
        }
//        Iterator<CategoryEntity> iterator = entities.iterator();
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), CategoryDTO.class);
        return results;
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO dto) throws IOException {
        CategoryEntity entity = new CategoryEntity();
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
            CategoryEntity old_entity = categoryRepository.findById(dto.getId()).get();
            String file_name = CloudinaryUtil.getNameImgFromUrlCloudinary(old_entity.getImage());
            System.out.println(file_name);
            String destroy = CloudinaryUtil.destroy(cloudinary, file_name);
            System.out.println(destroy);
            entity = categoryConverter.toEntity(dto, old_entity);
        }
        else {
            entity = categoryConverter.toEntity(dto);
        }
        return categoryConverter.toDTO(categoryRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            boolean exists = categoryRepository.existsById(id);
            if (exists) {
                categoryRepository.deleteById(id);
            }
        }
    }
}
