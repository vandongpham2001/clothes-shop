package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.converter.CategoryConverter;
import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.entity.CategoryEntity;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.service.ICategoryService;
import com.example.clothesshop.util.SlugUtil;
import com.example.clothesshop.util.UploadUtil;
import org.modelmapper.ModelMapper;
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
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        Page<CategoryEntity> entities = categoryRepository.findAll(pageable);
        for (CategoryEntity item : entities) {
            CategoryDTO dto = categoryConverter.toDTO(item);
            results.add(dto);
        }
        return results;
    }

    public List<CategoryDTO> findAll(Sort sort) {
        List<CategoryDTO> results = new ArrayList<>();
        Iterable<CategoryEntity> entities = categoryRepository.findAll(sort);
        for (CategoryEntity item : entities) {
            CategoryDTO dto = categoryConverter.toDTO(item);
            results.add(dto);
        }
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
            String img = null;
            img = UploadUtil.uploadCloudinary(cloudinary, dto.getFile());
            dto.setImage(img);
        }
        if (dto.getId() != null) {
            Optional<CategoryEntity> entity1 = categoryRepository.findById(dto.getId());
//            CategoryDTO old_dto = categoryConverter.toDTO(old_entity.get());
//            dto.setCreatedDate(old_dto.getCreatedDate());
//            dto.setCreatedBy(old_dto.getCreatedBy());
//            entity = categoryConverter.toEntity(dto, old_entity.get());
            entity = entity1.get();
            this.modelMapper.map(dto, entity);
        } else {
            entity = categoryConverter.toEntity(dto);
        }
//        entity = categoryConverter.toEntity(dto);
        return categoryConverter.toDTO(categoryRepository.save(entity));
//        return categoryConverter.toDTO(entity);
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
