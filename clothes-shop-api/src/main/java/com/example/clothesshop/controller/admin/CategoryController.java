package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Pageable pageable = null;
        Sort sortable = null;
        if (sort.equals("asc")) {
            sortable = Sort.by("createdBy").ascending();
        } else {
            sortable = Sort.by("createdBy").descending();
        }
        if (page != null && limit != null) {
            pageable = PageRequest.of(page-1, limit, sortable);
            return categoryService.findAll(pageable);
        }
        return categoryService.findAll(sortable);
    }

    @PostMapping
    public CategoryDTO create(@ModelAttribute CategoryDTO dto) throws IOException {
        return categoryService.save(dto);
    }

    @PutMapping
    public CategoryDTO update(@ModelAttribute CategoryDTO dto) throws IOException {
        return categoryService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids){
        categoryService.delete(ids);
    }
}
