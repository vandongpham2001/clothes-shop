package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.ProductDTO;
import com.example.clothesshop.dto.request.ProductRequest;
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

@RestController(value = "apiProductOfAdmin")
@CrossOrigin
@RequestMapping(path = "api/admin/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ProductDTO> pageProducts;
        List<ProductDTO> products;
        sortable = PagingUtils.sortByCreatedDate(sort);
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
    public ProductDTO create(@ModelAttribute ProductRequest dto){
//        ProductDTO dto = new ProductDTO();
//        dto = ObjectMapperUtils.map(request, ProductDTO.class);
        dto.setStatus(SystemConstant.ACTIVE_STATUS);
        return productService.save(dto);
    }

    @PutMapping
    public ProductDTO update(@ModelAttribute ProductRequest dto){
//        ProductDTO dto = new ProductDTO();
//        dto = ObjectMapperUtils.map(request, ProductDTO.class);
        return productService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return productService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public ProductDTO detail(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @PostMapping(value = "/detail")
    public ProductDTO getDetailFromFormData(@ModelAttribute("product") ProductDTO dto){
        return dto;
    }
}
