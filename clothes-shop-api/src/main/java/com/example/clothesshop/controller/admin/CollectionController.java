package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.CollectionDTO;
import com.example.clothesshop.dto.ProductToCollection;
import com.example.clothesshop.service.ICollectionService;
import com.example.clothesshop.util.PagingUtil;
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
@RequestMapping(path = "api/admin/collection")
public class CollectionController {
    @Autowired
    private ICollectionService collectionService;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<CollectionDTO> pageCollections;
        List<CollectionDTO> collections;
        sortable = PagingUtil.sort(sort);
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

    @PostMapping
    public CollectionDTO create(@ModelAttribute CollectionDTO dto){
        return collectionService.save(dto);
    }

    @PutMapping
    public CollectionDTO update(@ModelAttribute CollectionDTO dto){
        return collectionService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        collectionService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public CollectionDTO detail(@PathVariable("id") Long id){
        return collectionService.findById(id);
    }

    @PostMapping(value = "/product")
    public CollectionDTO addProductToCollection(@RequestBody ProductToCollection form){
        return collectionService.addProductToCollection(form.getCollection_id(), form.getProduct_id());
    }
    @DeleteMapping(value = "/product")
    public CollectionDTO removeProductFromCollection(@RequestBody ProductToCollection form){
        return collectionService.removeProductFromCollection(form.getCollection_id(), form.getProduct_id());
    }
    
}
