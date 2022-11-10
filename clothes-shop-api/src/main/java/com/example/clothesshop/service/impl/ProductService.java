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
import com.example.clothesshop.dto.request.FilterRequest;
import com.example.clothesshop.dto.request.ProductColorRequest;
import com.example.clothesshop.dto.request.ProductColorSizeRequest;
import com.example.clothesshop.dto.request.ProductRequest;
import com.example.clothesshop.dto.response.DetailProductResponse;
import com.example.clothesshop.dto.response.ProductResponse;
import com.example.clothesshop.dto.response.PromotionResponse;
import com.example.clothesshop.entity.*;
import com.example.clothesshop.repository.*;
import com.example.clothesshop.service.IProductService;
import com.example.clothesshop.utils.CloudinaryUtils;
import com.example.clothesshop.utils.DateUtils;
import com.example.clothesshop.utils.ObjectMapperUtils;
import com.example.clothesshop.utils.SlugUtils;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
    public Page<ProductDTO> findPageableByCategoryId(Long category_id, Integer status, Pageable pageable) {
        Page<ProductDTO> results;
        Page<ProductEntity> entities;
        entities = productRepository.findByStatusAndCategoryId(status, category_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductDTO.class);
        return results;
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long category_id, Integer status, Sort sort) {
        List<ProductDTO> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findByStatusAndCategoryId(status, category_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
    public Page<ProductDTO> findPageableByPromotionId(Long promotion_id, Integer status, Pageable pageable) {
        Page<ProductDTO> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndPromotionsId(status, promotion_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductDTO.class);
        return results;
    }

    @Override
    public List<ProductDTO> findByPromotionId(Long promotion_id, Integer status, Sort sort) {
        List<ProductDTO> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndPromotionsId(status, promotion_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
    public Page<ProductDTO> findPageableByCollectionId(Long collection_id, Integer status, Pageable pageable) {
        Page<ProductDTO> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndCollectionsId(status, collection_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductDTO.class);
        return results;
    }

    @Override
    public List<ProductDTO> findByCollectionId(Long collection_id, Integer status, Sort sort) {
        List<ProductDTO> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndCollectionsId(status, collection_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductDTO.class);
        return results;
    }

    @Override
    public Page<ProductResponse> findAllPageableInWeb(Pageable pageable) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntities(pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
//        for (ProductResponse item : results){
//            List<PromotionResponse> promotions = item.getPromotions();
//            for (int i=0; i<promotions.size(); i++){
//                Integer status = promotions.get(i).getStatus();
//                Date now = DateUtils.convertToDateViaInstant(LocalDateTime.now());
//                Date start = promotions.get(i).getStart_date();
//                Date end = promotions.get(i).getEnd_date();
//                System.out.println("Test: "+DateUtils.dateBetween(now, start, end));
//                if (status==SystemConstant.INACTIVE_STATUS || !DateUtils.dateBetween(now, start, end)){
//                    promotions.set(i, null);
//                }
//            }
//        }
        return results;
//        return null;
    }

    @Override
    public List<ProductResponse> findAllInWeb(Sort sort) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntities(sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
//        for (ProductResponse item : results){
//            List<PromotionResponse> promotions = item.getPromotions();
//            for (int i=0; i<promotions.size(); i++){
//                Integer status = promotions.get(i).getStatus();
//                Date now = DateUtils.convertToDateViaInstant(LocalDateTime.now());
//                Date start = promotions.get(i).getStart_date();
//                Date end = promotions.get(i).getEnd_date();
//                System.out.println("Test: "+DateUtils.dateBetween(now, start, end));
//                if (status==SystemConstant.INACTIVE_STATUS || !DateUtils.dateBetween(now, start, end)){
//                    promotions.set(i, null);
//                }
//            }
//        }
        return results;
//        return null;
    }

    @Override
    public Page<ProductResponse> findByCategoryPageableInWeb(Pageable pageable, Long category_id) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByCategory(category_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
    }

    @Override
    public List<ProductResponse> findByCategoryInWeb(Sort sort, Long category_id) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByCategory(category_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
        return results;
    }

    @Override
    public Page<ProductResponse> findByPromotionPageableInWeb(Pageable pageable, Long promotion_id) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndPromotionsId(SystemConstant.ACTIVE_STATUS, promotion_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
    }

    @Override
    public List<ProductResponse> findByPromotionInWeb(Sort sort, Long promotion_id) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndPromotionsId(SystemConstant.ACTIVE_STATUS, promotion_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
        return results;
    }

    @Override
    public Page<ProductResponse> findByCollectionPageableInWeb(Pageable pageable, Long collection_id) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndCollectionsId(SystemConstant.ACTIVE_STATUS, collection_id, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
    }

    @Override
    public List<ProductResponse> findByCollectionInWeb(Sort sort, Long collection_id) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByStatusAndCollectionsId(SystemConstant.ACTIVE_STATUS, collection_id, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
        return results;
    }

    @Override
    public Page<ProductResponse> findByFilterPageableInWeb(Pageable pageable, FilterRequest filter) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        if (filter.getMin_price()==null){
            filter.setMin_price(BigDecimal.valueOf(0));
        }
        if (filter.getMax_price()==null){
            filter.setMax_price(findMaxPriceProduct());
        }
        if (filter.getMin_price()==null && filter.getMax_price()==null){
            filter.setMin_price(BigDecimal.valueOf(0));
            filter.setMax_price(findMaxPriceProduct());
        }
        entities = productRepository.findProductEntitiesByFilter(filter.getColor_id(), filter.getSize_id(), filter.getMin_price(), filter.getMax_price(), pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
//        return null;
    }

    @Override
    public List<ProductResponse> findByFilterInWeb(Sort sort, FilterRequest filter) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        if (filter.getMin_price()==null){
            filter.setMin_price(BigDecimal.valueOf(0));
        }
        if (filter.getMax_price()==null){
            filter.setMax_price(findMaxPriceProduct());
        }
        if (filter.getMin_price()==null && filter.getMax_price()==null){
            filter.setMin_price(BigDecimal.valueOf(0));
            filter.setMax_price(findMaxPriceProduct());
        }
        entities = productRepository.findProductEntitiesByFilter(filter.getColor_id(), filter.getSize_id(), filter.getMin_price(), filter.getMax_price(), sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
        return results;
//        return null;
    }

    @Override
    public Page<ProductResponse> findByKeywordPageableInWeb(Pageable pageable, String keyword) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByKeyword(keyword, pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
    }

    @Override
    public List<ProductResponse> findByKeywordInWeb(Sort sort, String keyword) {
        List<ProductResponse> results;
        Iterable<ProductEntity> entities;
        entities = productRepository.findProductEntitiesByKeyword(keyword, sort);
        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
        return results;
    }

    @Override
    public Page<ProductResponse> findWeeklyBestPageableInWeb(Pageable pageable) {
        Page<ProductResponse> results;
        Page<ProductEntity> entities;
        entities = productRepository.findProductEntitiesWeeklyBest(pageable);
        results = productConverter.mapEntityPageIntoDtoPage(entities, ProductResponse.class);
        return results;
    }

    @Override
    public Integer countProductWeeklyBest() {
        return productRepository.countProductEntitiesWeeklyBest();
    }

//    @Override
//    public List<ProductResponse> findWeeklyBestInWeb(Sort sort) {
//        List<ProductResponse> results;
//        Iterable<ProductEntity> entities;
//        entities = productRepository.findProductEntitiesWeeklyBest(sort);
//        results = ObjectMapperUtils.mapAll(IterableUtils.toList(entities), ProductResponse.class);
//        return results;
////        return null;
//    }

    @Override
    public DetailProductResponse findDetailById(Long id) {
        ProductEntity entity = productRepository.findDetailProductById(id);
        DetailProductResponse result = ObjectMapperUtils.map(entity, DetailProductResponse.class);
//        List<PromotionResponse> promotions = result.getPromotions();
//        for (int i=0; i<promotions.size(); i++){
//            Integer status = promotions.get(i).getStatus();
//            Date now = DateUtils.convertToDateViaInstant(LocalDateTime.now());
//            Date start = promotions.get(i).getStart_date();
//            Date end = promotions.get(i).getEnd_date();
//            System.out.println("Test: "+DateUtils.dateBetween(now, start, end));
//            if (status==SystemConstant.INACTIVE_STATUS || !DateUtils.dateBetween(now, start, end)){
//                promotions.set(i, null);
//            }
//        }
        return result;
    }

    @Override
    public BigDecimal findMaxPriceProduct() {
        return productRepository.findMaxPriceProduct();
    }

    @Override
//    @Transactional
    public ProductDTO save(ProductRequest dto) {
        List<ProductColorRequest> listProductColorDTO = dto.getProduct_color();
        dto.setProduct_color(null);
        ProductEntity entity = new ProductEntity();
        if (dto.getId() != null) {
            ProductEntity old_entity = productRepository.findById(dto.getId()).get();
            if (old_entity.getImage() != null && dto.getFile() != null) {
                String file_name = CloudinaryUtils.getNameImgFromUrlCloudinary(old_entity.getImage());
                try {
                    String destroy = CloudinaryUtils.destroy(cloudinary, file_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            entity = productConverter.toEntity(dto, old_entity);
        } else {
            entity = productConverter.toEntity(dto);
            entity.setStatus(SystemConstant.ACTIVE_STATUS);
        }
        if (dto.getName() != null) {
            entity.setSlug(SlugUtils.toSlug(dto.getName()));
        }
        if (dto.getFile() != null) {
            try {
                String img = CloudinaryUtils.upload(cloudinary, dto.getFile());
                entity.setImage(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (dto.getCategory_id() != null) {
            entity.setCategory(categoryRepository.findById(dto.getCategory_id()).get());
        }
        ProductDTO savedProduct = productConverter.toDTO(productRepository.save(entity));
        if (listProductColorDTO != null) {
            for (ProductColorRequest productColorDTO : listProductColorDTO) {
                productColorDTO.setProduct_id(savedProduct.getId());
                List<ProductColorSizeRequest> listProductColorSizeDTO = productColorDTO.getProduct_color_size();
                MultipartFile[] listProductColorImage = null;
                listProductColorImage = productColorDTO.getProduct_color_image().get(0).getFile();
                productColorDTO.setProduct_color_size(null);
                productColorDTO.setProduct_color_image(null);
                ProductColorEntity productColorEntity = new ProductColorEntity();
                if (productColorDTO.getId() != null) {
                    ProductColorEntity oldProductColorEntity = productColorRepository.findById(productColorDTO.getId()).get();
                    if (oldProductColorEntity.getThumbnail() != null && productColorDTO.getFile() != null) {
                        String file_name = CloudinaryUtils.getNameImgFromUrlCloudinary(oldProductColorEntity.getThumbnail());
                        try {
                            String destroy = CloudinaryUtils.destroy(cloudinary, file_name);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    productColorEntity = productColorConverter.toEntity(productColorDTO, oldProductColorEntity);
                } else {
                    productColorEntity = productColorConverter.toEntity(productColorDTO);
                }
                if (productColorDTO.getFile() != null) {
                    try {
                        String img = CloudinaryUtils.upload(cloudinary, productColorDTO.getFile());
                        productColorEntity.setThumbnail(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                    for (ProductColorSizeRequest productColorSizeDTO : listProductColorSizeDTO) {
                        productColorSizeDTO.setProduct_color_id(savedProductColor.getId());
                        ProductColorSizeEntity productColorSizeEntity = new ProductColorSizeEntity();
                        if (productColorSizeDTO.getId() != null) {
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
                            String img = CloudinaryUtils.upload(cloudinary, productColorImage);
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
