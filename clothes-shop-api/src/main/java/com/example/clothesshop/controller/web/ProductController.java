package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.request.FilterRequest;
import com.example.clothesshop.dto.response.DetailProductResponse;
import com.example.clothesshop.dto.response.ProductResponse;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController(value = "productApiOfWeb")
@CrossOrigin
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/max-price")
    public BigDecimal getMaxPriceProduct() {
        return productService.findMaxPriceProduct();
    }

    // All Product
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findAllPageableInWeb(pageable);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findAllInWeb(sortable);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product Detail
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        DetailProductResponse product = productService.findDetailById(id);
        response.put("product", product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product By Category
    @GetMapping("/category/{id}")
    public ResponseEntity<Map<String, Object>> getByCategory(@PathVariable("id") Long id,
                                                             @RequestParam(value = "page", required = false) Integer page,
                                                             @RequestParam(value = "limit", required = false) Integer limit,
                                                             @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findByCategoryPageableInWeb(pageable, id);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findByCategoryInWeb(sortable, id);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product By Promotion
    @GetMapping("/promotion/{id}")
    public ResponseEntity<Map<String, Object>> getByPromotion(@PathVariable("id") Long id,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "limit", required = false) Integer limit,
                                                              @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findByPromotionPageableInWeb(pageable, id);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findByPromotionInWeb(sortable, id);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product By Collection
    @GetMapping("/collection/{id}")
    public ResponseEntity<Map<String, Object>> getByCollection(@PathVariable("id") Long id,
                                                               @RequestParam(value = "page", required = false) Integer page,
                                                               @RequestParam(value = "limit", required = false) Integer limit,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findByCollectionPageableInWeb(pageable, id);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findByCollectionInWeb(sortable, id);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product Weekly Best
    @GetMapping("/weekly_best")
    public ResponseEntity<Map<String, Object>> getProductWeeklyBest(@RequestParam(value = "page", required = false) Integer page,
                                                                    @RequestParam(value = "limit", required = false) Integer limit,
                                                                    @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = null;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sort_by_created_date(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findWeeklyBestPageableInWeb(pageable);
            products = pageProducts.getContent();
            Integer total_item = productService.countProductWeeklyBest();
            Integer total_page = (int) Math.ceil((double) total_item / limit);
            response.put("currentPage", pageProducts.getNumber() + 1);
//            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalItems", total_item);
            response.put("totalPages", total_page);
        } else {
            limit = productService.countProductWeeklyBest();
//            limit = 100;
            pageable = PageRequest.of(0, limit, sortable);
            pageProducts = productService.findWeeklyBestPageableInWeb(pageable);
            products = pageProducts.getContent();
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Product New
    @GetMapping("/new")
    public ResponseEntity<Map<String, Object>> getProductNew(@RequestParam(value = "page", required = false) Integer page,
                                                             @RequestParam(value = "limit", required = false) Integer limit,
                                                             @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortById(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findAllPageableInWeb(pageable);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findAllInWeb(sortable);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    Product Filter
    @PostMapping("/filter")
    public ResponseEntity<Map<String, Object>> getByFilter(@RequestBody FilterRequest filter,
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                           @RequestParam(value = "limit", required = false) Integer limit,
                                                           @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable = null;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products; 
        if (Objects.equals(sort, "asc") || Objects.equals(sort, "desc")){
            sortable = PagingUtils.sortById(sort);
        }
        if (Objects.equals(sort, "price_asc") || Objects.equals(sort, "price_desc")){
            sortable = PagingUtils.sortByPrice(sort);
        }
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findByFilterPageableInWeb(pageable, filter);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findByFilterInWeb(sortable, filter);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    Product Search by name
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getByKeyword(@RequestParam(value = "keyword") String keyword,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "limit", required = false) Integer limit,
                                                            @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductResponse> pageProducts;
        List<ProductResponse> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageProducts = productService.findByKeywordPageableInWeb(pageable, keyword);
            products = pageProducts.getContent();
            response.put("currentPage", pageProducts.getNumber() + 1);
            response.put("totalItems", pageProducts.getTotalElements());
            response.put("totalPages", pageProducts.getTotalPages());
        } else {
            products = productService.findByKeywordInWeb(sortable, keyword);
        }
        response.put("products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    Menu
}
