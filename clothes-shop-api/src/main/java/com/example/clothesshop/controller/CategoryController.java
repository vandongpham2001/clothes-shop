package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryService.findAll();
    }
    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto){
        return categoryService.save(dto);
    }
}
