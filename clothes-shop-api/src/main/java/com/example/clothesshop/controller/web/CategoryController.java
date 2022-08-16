package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.CategoryDTO;
import com.example.clothesshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "categoryApiOfWeb")
@RequestMapping(path = "api/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("parent_category")
    public List<CategoryDTO> findAll(){
        return categoryService.findAllParentCategory();
    }
    @GetMapping("{parent_id}")
    public List<CategoryDTO> findCategoryByParentId(@PathVariable("parent_id") long id){
        return categoryService.findByParentId(id);
    }
    @GetMapping("/detail/{id}")
    public CategoryDTO findCategoryById(@PathVariable("id") long id){
        return categoryService.findById(id);
    }
}
