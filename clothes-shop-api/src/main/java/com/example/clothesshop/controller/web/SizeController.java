package com.example.clothesshop.controller.web;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.service.IProductService;
import com.example.clothesshop.service.ISizeService;
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

@RestController(value = "apiSizeOfWeb")
@RequestMapping(path = "api/size")
public class SizeController {
    @Autowired
    private ISizeService sizeService;
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<SizeDTO> pageSizes;
        List<SizeDTO> sizes;
        sortable = PagingUtils.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageSizes = sizeService.findAllPageable(SystemConstant.ACTIVE_STATUS, pageable);
            sizes = pageSizes.getContent();
            response.put("currentPage", pageSizes.getNumber() + 1);
            response.put("totalItems", pageSizes.getTotalElements());
            response.put("totalPages", pageSizes.getTotalPages());
        } else {
            sizes = sizeService.findAll(SystemConstant.ACTIVE_STATUS, sortable);
        }
        response.put("sizes", sizes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    SizeByProduct
    @GetMapping("/product/{product_id}")
    public ResponseEntity<Map<String, Object>> getByProductId(@PathVariable("product_id") Long product_id,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "limit", required = false) Integer limit,
                                                              @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<SizeDTO> pageSizes;
        List<SizeDTO> sizes;
        sortable = PagingUtils.sort(sort);
        ProductDTO product = productService.findById(product_id);
        product.setProduct_color(null);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageSizes = sizeService.findPageableByProductId(product_id, SystemConstant.ACTIVE_STATUS, pageable);
            sizes = pageSizes.getContent();
            response.put("currentPage", pageSizes.getNumber() + 1);
            response.put("totalItems", pageSizes.getTotalElements());
            response.put("totalPages", pageSizes.getTotalPages());
        } else {
            sizes = sizeService.findByProductId(product_id, SystemConstant.ACTIVE_STATUS, sortable);
        }
        response.put("product", product);
        response.put("sizes", sizes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
