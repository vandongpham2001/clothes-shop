package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.service.IProductService;
import com.example.clothesshop.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiProductOfAdmin")
@RequestMapping(path = "api/admin/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductDTO> pageProducts;
        List<ProductDTO> products;
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findAllPageable(status, pageable);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findAll(status, sortable);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ProductDTO create(@ModelAttribute ProductDTO dto) throws IOException {
        return productService.save(dto);
    }

    @PutMapping
    public ProductDTO update(@ModelAttribute ProductDTO dto) throws IOException {
        return productService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        productService.delete(ids);
    }

    @PostMapping(value = "/detail")
    public ProductDTO detail(@ModelAttribute("product") ProductDTO dto) throws IOException {
        return dto;
    }
}
