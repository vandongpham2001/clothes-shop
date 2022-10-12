package com.example.clothesshop.controller.web;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.service.IColorService;
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

@RestController(value = "apiColorOfWeb")
@RequestMapping(path = "api/color")
public class ColorController {
    //    ColorByProduct
//    All Color
    @Autowired
    private IColorService colorService;
    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ColorDTO> pageColors;
        List<ColorDTO> colors;
        sortable = PagingUtils.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageColors = colorService.findAllPageable(SystemConstant.ACTIVE_STATUS, pageable);
            colors = pageColors.getContent();
            response.put("currentPage", pageColors.getNumber() + 1);
            response.put("totalItems", pageColors.getTotalElements());
            response.put("totalPages", pageColors.getTotalPages());
        } else {
            colors = colorService.findAll(SystemConstant.ACTIVE_STATUS, sortable);
        }
        response.put("colors", colors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{product_id}")
    public ResponseEntity<Map<String, Object>> getByProductId(@PathVariable("product_id") Long product_id,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "limit", required = false) Integer limit,
                                                              @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ColorDTO> pageColors;
        List<ColorDTO> colors;
        sortable = PagingUtils.sort(sort);
        ProductDTO product = productService.findById(product_id);
        product.setProduct_color(null);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageColors = colorService.findAllPageable(SystemConstant.ACTIVE_STATUS, pageable);
            colors = pageColors.getContent();
            response.put("currentPage", pageColors.getNumber() + 1);
            response.put("totalItems", pageColors.getTotalElements());
            response.put("totalPages", pageColors.getTotalPages());
        } else {
            colors = colorService.findAll(SystemConstant.ACTIVE_STATUS, sortable);
        }
        response.put("product", product);
        response.put("colors", colors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
