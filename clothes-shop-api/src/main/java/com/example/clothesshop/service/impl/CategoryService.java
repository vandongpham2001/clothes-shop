package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.CategoryConverter;
import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.service.ICategoryService;
import com.example.clothesshop.utils.CloudinaryUtils;
import com.example.clothesshop.utils.ObjectMapperUtils;
import com.example.clothesshop.utils.SlugUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    private Cloudinary cloudinary;

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
        List<CategoryDTO> results;
        Iterable<CategoryEntity> entities;
        if (status!=null) {
            entities = categoryRepository.findByStatus(status, sort);
        } else {
            entities = categoryRepository.findAll(sort);
        }
//        Iterator<CategoryEntity> iterator = entities.iterator();
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), CategoryDTO.class);
        return results;
    }

    @Override
//    @Transactional
    public CategoryDTO save(CategoryDTO dto){
        CategoryEntity entity;
        if (dto.getName() != null) {
            dto.setSlug(SlugUtils.toSlug(dto.getName()));
        }
        if (dto.getFile() != null) {
            try {
                String img = CloudinaryUtils.upload(cloudinary, dto.getFile()[0]);
                dto.setImage(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            for (MultipartFile file : dto.getFile()){
//                String img = CloudinaryUtil.upload(cloudinary, file);
//            }
        }
        if (dto.getId() != null) {
            CategoryEntity old_entity = categoryRepository.findById(dto.getId()).get();
            if (old_entity.getImage() != null && dto.getFile() != null) {
                String file_name = CloudinaryUtils.getNameImgFromUrlCloudinary(old_entity.getImage());
//            System.out.println(file_name);
//            String destroy = null;
                try {
                    String destroy = CloudinaryUtils.destroy(cloudinary, file_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            System.out.println(destroy);
            }
            entity = categoryConverter.toEntity(dto, old_entity);
        }
        else {
            entity = categoryConverter.toEntity(dto);
            entity.setStatus(SystemConstant.ACTIVE_STATUS);
        }
        return categoryConverter.toDTO(categoryRepository.save(entity));
    }

    @Override
//    @Transactional
    public String delete(long[] ids) {
        for (long id : ids) {
            CategoryEntity exists = categoryRepository.findById(id).get();
            if (exists != null) {
//                categoryRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                categoryRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public List<CategoryDTO> findAllParentCategory() {
        List<CategoryEntity> entities = categoryRepository.findParentCategory();
        return ObjectMapperUtils.mapAll(entities, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findByParentId(Long parent_id) {
        List<CategoryEntity> entities = categoryRepository.findByParentId(parent_id);
        return ObjectMapperUtils.mapAll(entities, CategoryDTO.class);
    }

    @Override
    public CategoryDTO findById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        return categoryConverter.toDTO(entity.get());
    }
}
