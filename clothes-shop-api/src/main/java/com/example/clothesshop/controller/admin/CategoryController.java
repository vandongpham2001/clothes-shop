package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.service.ICategoryService;
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

@RestController(value = "categoryApiOfAdmin")
@RequestMapping(path = "api/admin/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<CategoryDTO> pageCategories;
        List<CategoryDTO> categories;
//        if (sort.equals("asc")) {
//            sortable = Sort.by(Sort.Direction.ASC, "createdDate");
//        }
//        if (sort.equals("desc")) {
//            sortable = Sort.by(Sort.Direction.DESC, "createdDate");
//        }
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageCategories = categoryService.findAllPageable(status, pageable);
            categories = pageCategories.getContent();
            response.put("currentPage", pageCategories.getNumber() + 1);
            response.put("totalItems", pageCategories.getTotalElements());
            response.put("totalPages", pageCategories.getTotalPages());
        } else {
            categories = categoryService.findAll(status, sortable);
        }
        response.put("categories", categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public CategoryDTO create(@ModelAttribute CategoryDTO dto){
        return categoryService.save(dto);
    }

    @PutMapping
    public CategoryDTO update(@ModelAttribute CategoryDTO dto){
        return categoryService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }
}
