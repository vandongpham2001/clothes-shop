package com.example.clothesshop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.ProductColorConverter;
import com.example.clothesshop.converter.ProductColorImageConverter;
import com.example.clothesshop.converter.ProductColorSizeConverter;
import com.example.clothesshop.converter.ProductConverter;
import com.example.clothesshop.dto.ProductColorDTO;
import com.example.clothesshop.dto.ProductColorImageDTO;
import com.example.clothesshop.dto.ProductColorSizeDTO;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.entity.*;
import com.example.clothesshop.repository.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ProductColorRepository productColorRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ProductColorSizeRepository productColorSizeRepository;
    @Autowired
    private ProductColorImageRepository productColorImageRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductColorConverter productColorConverter;
    @Autowired
    private ProductColorSizeConverter productColorSizeConverter;
    @Autowired
    private ProductColorImageConverter productColorImageConverter;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Page<ProductDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<ProductDTO> results;
        Page<ProductEntity> entities;
        if (status != null) {
            entities = productRepository.findByStatus(status, pageable);
        } else {
            entities = productRepository.findAll(pageable);
        }
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductDTO.class);
        return results;
    }

    public List<ProductDTO> findAll(Integer status, Sort sort) {
        List<ProductDTO> results;
        Iterable<ProductEntity> entities;
        if (status != null) {
            entities = productRepository.findByStatus(status, sort);
        } else {
            entities = productRepository.findAll(sort);
        }
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
//    @Transactional
    public ProductDTO save(ProductDTO dto) {
        if (dto.getName() != null) {
            dto.setSlug(SlugUtil.toSlug(dto.getName()));
        }
        if (dto.getFile() != null) {
            try {
                String img = CloudinaryUtil.upload(cloudinary, dto.getFile()[0]);
                dto.setImage(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<ProductColorDTO> listProductColorDTO = dto.getProduct_color();
        dto.setProduct_color(null);
        ProductEntity entity = new ProductEntity();
        if (dto.getId() != null) {
            ProductEntity old_entity = productRepository.findById(dto.getId()).get();
            if (old_entity.getImage() != null && dto.getFile() != null) {
                String file_name = CloudinaryUtil.getNameImgFromUrlCloudinary(old_entity.getImage());
                try {
                    String destroy = CloudinaryUtil.destroy(cloudinary, file_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            entity = productConverter.toEntity(dto, old_entity);
        } else {
            entity = productConverter.toEntity(dto);
        }
        if (dto.getCategory_id() != null) {
            entity.setCategory(categoryRepository.findById(dto.getCategory_id()).get());
        }
        ProductDTO savedProduct = productConverter.toDTO(productRepository.save(entity));
        if (listProductColorDTO != null) {
            for (ProductColorDTO productColorDTO : listProductColorDTO) {
                productColorDTO.setProduct_id(savedProduct.getId());
                if (productColorDTO.getFile() != null) {
                    try {
                        String img = CloudinaryUtil.upload(cloudinary, dto.getFile()[0]);
                        productColorDTO.setThumbnail(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                List<ProductColorSizeDTO> listProductColorSizeDTO = productColorDTO.getProduct_color_size();
                MultipartFile[] listProductColorImage = null;
                listProductColorImage = productColorDTO.getProduct_color_image().get(0).getFile();
                productColorDTO.setProduct_color_size(null);
                productColorDTO.setProduct_color_image(null);
                ProductColorEntity productColorEntity = new ProductColorEntity();
                if (productColorDTO.getId() != null){
                    ProductColorEntity oldProductColorEntity = productColorRepository.findById(productColorDTO.getId()).get();
                    if (oldProductColorEntity.getThumbnail() != null && productColorDTO.getFile() != null) {
                        String file_name = CloudinaryUtil.getNameImgFromUrlCloudinary(oldProductColorEntity.getThumbnail());
                        try {
                            String destroy = CloudinaryUtil.destroy(cloudinary, file_name);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    productColorEntity = productColorConverter.toEntity(productColorDTO, oldProductColorEntity);
                }
                else {
                    productColorEntity = productColorConverter.toEntity(productColorDTO);
                }
                if (productColorDTO.getProduct_id() != null) {
                    productColorEntity.setProduct(productRepository.findById(productColorDTO.getProduct_id()).get());
                }
                if (productColorDTO.getColor_id() != null) {
                    productColorEntity.setColor(colorRepository.findById(productColorDTO.getColor_id()).get());
                }
                ProductColorDTO savedProductColor = productColorConverter.toDTO(productColorRepository.save(productColorEntity));
//                MultipartFile[] listProductColorImage = savedProductColor.getProduct_color_image().get(0).getFile();
//                MultipartFile[] listProductColorImage = productColorDTO.getProduct_color_image().iterator().next().getFile();
                if (listProductColorSizeDTO != null) {
                    for (ProductColorSizeDTO productColorSizeDTO : listProductColorSizeDTO) {
                        productColorSizeDTO.setProduct_color_id(savedProductColor.getId());
                        ProductColorSizeEntity productColorSizeEntity = new ProductColorSizeEntity();
                        if (productColorSizeDTO.getId() != null){
                            ProductColorSizeEntity oldProductColorSizeEntity = productColorSizeRepository.findById(productColorSizeDTO.getId()).get();
                            productColorSizeEntity = productColorSizeConverter.toEntity(productColorSizeDTO, oldProductColorSizeEntity);
                        } else {
                            productColorSizeEntity = productColorSizeConverter.toEntity(productColorSizeDTO);
                        }
                        if (productColorSizeDTO.getProduct_color_id() != null) {
                            productColorSizeEntity.setProduct_color(
                                    productColorRepository.findById(productColorSizeDTO.getProduct_color_id()).get());
                        }
                        if (productColorSizeDTO.getSize_id() != null) {
                            productColorSizeEntity.setSize(sizeRepository.findById(productColorSizeDTO.getSize_id()).get());
                        }
                        ProductColorSizeDTO savedProductColorSize = productColorSizeConverter.toDTO(productColorSizeRepository.save(productColorSizeEntity));
                    }
                }
                if (listProductColorImage != null) {
                    productColorImageRepository.deleteByProduct_color(savedProductColor.getId());
                    for (MultipartFile productColorImage : listProductColorImage) {
                        ProductColorImageEntity productColorImageEntity = new ProductColorImageEntity();
                        productColorImageEntity.setProduct_color(productColorRepository.findById(savedProductColor.getId()).get());
                        try {
                            String img = CloudinaryUtil.upload(cloudinary, productColorImage);
                            productColorImageEntity.setPath(img);
                            if (!productColorImageEntity.getPath().isEmpty()) {
                                ProductColorImageDTO savedProductColorImage = productColorImageConverter.toDTO(productColorImageRepository.save(productColorImageEntity));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
//        return productConverter.toDTO(productRepository.findById(savedProduct.getId()).get());
        ProductEntity finalEntity = productRepository.findById(savedProduct.getId()).get();
        finalEntity.setProduct_color(productColorRepository.findByProductId(finalEntity.getId()));
        return productConverter.toDTO(finalEntity);
    }

    @Override
//    @Transactional
    public String delete(long[] ids) {
        for (long id : ids) {
            ProductEntity exists = productRepository.findById(id).get();
            if (exists != null) {
//                productRepository.deleteById(id);
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                productRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public ProductDTO findById(long id) {
        ProductEntity entity = productRepository.findById(id).get();
        return productConverter.toDTO(entity);
    }
}
