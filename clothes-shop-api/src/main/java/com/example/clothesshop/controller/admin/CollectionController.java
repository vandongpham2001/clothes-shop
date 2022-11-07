package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.dto.request.CollectionRequest;
import com.example.clothesshop.dto.request.ProductToCollection;
import com.example.clothesshop.service.ICollectionService;
import com.example.clothesshop.service.IProductService;
import com.example.clothesshop.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiCollectionOfAdmin")
@CrossOrigin
@RequestMapping(path = "api/admin/collection")
public class CollectionController {
    @Autowired
    private ICollectionService collectionService;
    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<CollectionDTO> pageCollections;
        List<CollectionDTO> collections;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageCollections = collectionService.findAllPageable(status, pageable);
            collections = pageCollections.getContent();
            response.put("currentPage", pageCollections.getNumber() + 1);
            response.put("totalItems", pageCollections.getTotalElements());
            response.put("totalPages", pageCollections.getTotalPages());
        } else {
            collections = collectionService.findAll(status, sortable);
        }
        response.put("collections", collections);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{collection_id}/product")
    public ResponseEntity<Map<String, Object>> getProductByCollectionId(@PathVariable("collection_id") Long collection_id,
                                                                        @RequestParam(value = "page", required = false) Integer page,
                                                                        @RequestParam(value = "limit", required = false) Integer limit,
                                                                        @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                                        @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductDTO> pageCollections;
        List<ProductDTO> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        CollectionDTO collection = collectionService.findById(collection_id);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageCollections = productService.findPageableByCollectionId(collection_id, status, pageable);
            products = pageCollections.getContent();
            response.put("currentPage", pageCollections.getNumber() + 1);
            response.put("totalItems", pageCollections.getTotalElements());
            response.put("totalPages", pageCollections.getTotalPages());
        } else {
            products = productService.findByCollectionId(collection_id, status, sortable);
        }
        response.put("collection", collection);
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public CollectionDTO create(@ModelAttribute CollectionRequest dto) {
//        CollectionDTO dto = new CollectionDTO();
//        dto = ObjectMapperUtils.map(request, CollectionDTO.class);
        dto.setStatus(SystemConstant.ACTIVE_STATUS);
        return collectionService.save(dto);
    }

    @PutMapping
    public CollectionDTO update(@ModelAttribute CollectionRequest dto) {
//        CollectionDTO dto = new CollectionDTO();
//        dto = ObjectMapperUtils.map(request, CollectionDTO.class);
        return collectionService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return collectionService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public CollectionDTO detail(@PathVariable("id") Long id) {
        return collectionService.findById(id);
    }

    @PostMapping(value = "/product")
    public CollectionDTO addProductToCollection(@RequestBody ProductToCollection form) {
        return collectionService.addProductToCollection(form.getCollection_id(), form.getProduct_id());
    }

    @DeleteMapping(value = "/product")
    public CollectionDTO removeProductFromCollection(@RequestBody ProductToCollection form) {
        return collectionService.removeProductFromCollection(form.getCollection_id(), form.getProduct_id());
    }

}
